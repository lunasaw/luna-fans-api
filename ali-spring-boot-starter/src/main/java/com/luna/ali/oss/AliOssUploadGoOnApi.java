package com.luna.ali.oss;

import java.io.ByteArrayInputStream;
import java.util.List;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.AppendObjectRequest;
import com.aliyun.oss.model.AppendObjectResult;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.UploadFileRequest;
import com.luna.ali.config.AliOssConfigProperties;
import org.apache.commons.lang3.StringUtils;

/**
 * @Package: com.luna.ali.oss
 * @ClassName: AliOssUploadGoOnApi
 * @Author: luna
 * @CreateTime: 2020/8/22 15:26
 * @Description:
 */
public class AliOssUploadGoOnApi {

    /**
     * 追加上传
     *
     * @param bucketName
     * @param contents
     * @param objectName
     * @param aliOssConfigProperties
     */
    public static void uploadGoOn(String bucketName, List<String> contents, String objectName,
        AliOssConfigProperties aliOssConfigProperties) {
        // 创建OSSClient实例。
        OSS ossClient = aliOssConfigProperties.getOssClient(false);

        ObjectMetadata meta = new ObjectMetadata();
        // 指定上传的内容类型。
        meta.setContentType("text/plain");

        // 通过AppendObjectRequest设置多个参数。
        AppendObjectRequest appendObjectRequest =
            new AppendObjectRequest(bucketName, objectName, new ByteArrayInputStream(contents.get(0).getBytes()), meta);

        // 通过AppendObjectRequest设置单个参数。
        // 设置存储空间名称。
        // appendObjectRequest.setBucketName("<yourBucketName>");
        // 设置文件名称。
        // appendObjectRequest.setKey("<yourObjectName>");
        // 设置待追加的内容。有两种可选类型：InputStream类型和File类型。这里为InputStream类型。
        // appendObjectRequest.setInputStream(new ByteArrayInputStream(content1.getBytes()));
        // 设置待追加的内容。有两种可选类型：InputStream类型和File类型。这里为File类型。
        // appendObjectRequest.setFile(new File("<yourLocalFile>"));
        // 指定文件的元信息，第一次追加时有效。
        // appendObjectRequest.setMetadata(meta);

        // 第一次追加。
        // 设置文件的追加位置。
        appendObjectRequest.setPosition(0L);
        AppendObjectResult appendObjectResult = ossClient.appendObject(appendObjectRequest);
        // 文件的64位CRC值。此值根据ECMA-182标准计算得出。
        System.out.println(appendObjectResult.getObjectCRC());

        for (int i = 1; i < contents.size(); i++) {
            appendObjectRequest.setPosition(appendObjectResult.getNextPosition());
            appendObjectRequest.setInputStream(new ByteArrayInputStream(contents.get(i).getBytes()));
            appendObjectResult = ossClient.appendObject(appendObjectRequest);
        }

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    /**
     * 断点续传
     * 
     * @param bucketName
     * @param localFile
     * @param checkpointFile
     * @param objectName
     * @param aliOssConfigProperties
     * @throws Throwable
     */
    public void uploadBreakPoint(String bucketName, String localFile, String checkpointFile, Integer taskNum,
        String objectName,
        AliOssConfigProperties aliOssConfigProperties) throws Throwable {
        // 创建OSSClient实例。
        OSS ossClient = aliOssConfigProperties.getOssClient(false);

        ObjectMetadata meta = new ObjectMetadata();
        // 指定上传的内容类型。
        meta.setContentType("text/plain");

        // 通过UploadFileRequest设置多个参数。
        UploadFileRequest uploadFileRequest = new UploadFileRequest(bucketName, objectName);

        // 通过UploadFileRequest设置单个参数。
        // 设置存储空间名称。
        // uploadFileRequest.setBucketName("<yourBucketName>");
        // 设置文件名称。
        // uploadFileRequest.setKey("<yourObjectName>");

        // 指定上传的本地文件。
        uploadFileRequest.setUploadFile(localFile);
        if (taskNum != null) {
            // 指定上传并发线程数，默认为1。
            uploadFileRequest.setTaskNum(taskNum);
        }
        // 指定上传的分片大小。
        uploadFileRequest.setPartSize(1 * 1024 * 1024);
        // 开启断点续传，默认关闭。
        uploadFileRequest.setEnableCheckpoint(true);
        if (StringUtils.isNotEmpty(checkpointFile)) {
            // 记录本地分片上传结果的文件。
            uploadFileRequest.setCheckpointFile(checkpointFile);
        }
        // 文件的元数据。
        uploadFileRequest.setObjectMetadata(meta);
        // 设置上传成功回调，参数为Callback类型。
        uploadFileRequest.setCallback(AliOssUtil.getCallback(aliOssConfigProperties.getServerUrl()));

        // 断点续传上传。
        ossClient.uploadFile(uploadFileRequest);

        // 关闭OSSClient。
        ossClient.shutdown();
    }
}

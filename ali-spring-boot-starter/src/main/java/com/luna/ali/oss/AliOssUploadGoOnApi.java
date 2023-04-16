package com.luna.ali.oss;

import java.io.ByteArrayInputStream;
import java.util.List;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.AppendObjectRequest;
import com.aliyun.oss.model.AppendObjectResult;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.UploadFileRequest;
import com.luna.ali.config.AliConfigProperties;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @author Luna@win10
 * @date 2020/4/20 11:46
 */
@Component
public class AliOssUploadGoOnApi {

    @Autowired
    private AliOssClientSupport aliOssClientSupport;

    private static final Logger log = LoggerFactory.getLogger(AliOssUploadApi.class);

    public AppendObjectResult uploadGoOn(String bucketName, String objectName, List<String> contents, String contentType) {

        // 指定上传的内容类型。
        if (StringUtils.isEmpty(contentType)) {
            contentType = "text/plain";
        }

        ObjectMetadata meta = new ObjectMetadata();

        Assert.notNull(contentType, "内容类型不能为空");
        Assert.notNull(bucketName, "存储空间名称不能为空");

        meta.setContentType(contentType);
        AppendObjectResult objectResult = null;
        for (String content : contents) {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(content.getBytes());

            // 通过AppendObjectRequest设置多个参数。
            AppendObjectRequest appendObjectRequest =
                new AppendObjectRequest(bucketName, objectName, inputStream, meta);

            objectResult = uploadGoOn(appendObjectRequest);
            appendObjectRequest.setPosition(objectResult.getNextPosition());
            appendObjectRequest.setInitCRC(objectResult.getClientCRC());
        }

        return objectResult;
    }

    /**
     * 追加上传
     *
     * @param appendObjectRequest
     */
    public AppendObjectResult uploadGoOn(AppendObjectRequest appendObjectRequest) {

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


        return aliOssClientSupport.getInstanceClient().appendObject(appendObjectRequest);
    }

    /**
     * 断点续传
     * 
     * @param bucketName
     * @param localFile
     * @param checkpointFile
     * @param objectName
     * @param aliConfigProperties
     * @throws Throwable
     */
    public void uploadBreakPoint(String bucketName, String localFile, String checkpointFile, Integer taskNum,
        String objectName,
        AliConfigProperties aliConfigProperties) throws Throwable {
        // 创建aliOssClientSupport.getInstanceClient()实例。
        aliOssClientSupport.getInstanceClient();

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
        uploadFileRequest.setCallback(AliOssUtil.getCallback(aliConfigProperties.getCallbackUrl()));

        // 断点续传上传。
        aliOssClientSupport.getInstanceClient().uploadFile(uploadFileRequest);

        // 关闭aliOssClientSupport.getInstanceClient()。
        aliOssClientSupport.getInstanceClient().shutdown();
    }
}

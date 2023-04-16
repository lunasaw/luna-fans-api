package com.luna.ali.oss;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Objects;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.*;
import com.luna.common.net.base.HttpBaseUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Luna@win10
 * @date 2020/4/20 11:46
 */
public class AliOssDownloadApi {

    public AliOssDownloadApi(OSS ossClient) {
        this.ossClient = ossClient;
    }

    private OSS                 ossClient;

    private static final Logger log = LoggerFactory.getLogger(AliOssUploadApi.class);

    /**
     * 文本读取下载
     * 
     * @param bucketName
     * @param objectName
     * @return
     */
    public String downloadByStream(String bucketName, String objectName) {

        // ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
        OSSObject ossObject = ossClient.getObject(bucketName, objectName);

        // 读取文件内容。
        BufferedReader reader = new BufferedReader(new InputStreamReader(ossObject.getObjectContent()));
        String s = HttpBaseUtils.readWithReader(reader);
        // 数据读取完成后，获取的流必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作。
        try {
            reader.close();
        } catch (IOException e) {
            log.error("downloadByStream::bucketName = {}, objectName = {} ", bucketName, objectName, e);
        }

        // 关闭OSSClient。
        ossClient.shutdown();
        return s;
    }

    /**
     * 下载文件到本地
     * 
     * @param bucketName
     * @param objectName
     * @param loaclFile
     * 
     */
    public void downloadByFile(String bucketName, String objectName, String loaclFile) {

        // 下载OSS文件到本地文件。如果指定的本地文件存在会覆盖，不存在则新建。
        ossClient.getObject(new GetObjectRequest(bucketName, objectName), new File(loaclFile));

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    /**
     * 条件下载
     * If-Modified-Since 如果指定的时间早于实际修改时间，则正常传输文件，否则返回错误（304 Not modified）。
     * If-Unmodified-Since 如果指定的时间等于或者晚于文件实际修改时间，则正常传输文件，否则返回错误（412 Precondition failed）。
     * If-Match 如果指定的ETag和OSS文件的ETag匹配，则正常传输文件，否则返回错误（412 Precondition failed）。
     * If-None-Match 如果指定的ETag和OSS文件的ETag不匹配，则正常传输文件，否则返回错误（304 Not modified）。
     * 
     * @param bucketName
     * @param objectName
     * @param localFile
     */
    public void downloadByCondition(String bucketName, String objectName, String localFile) {

        GetObjectRequest request = new GetObjectRequest(bucketName, objectName);
        // 设置限定条件。
        // 修改时间比当前时间早
        request.setModifiedSinceConstraint(new Date());

        // 下载OSS文件到本地文件。
        ossClient.getObject(request, new File(localFile));

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    /**
     * 断点续传
     * 
     * @param bucketName
     * @param objectName
     * @param loaclFile
     * @param checkFile
     */
    public ObjectMetadata downloadByGoOn(String bucketName, String objectName, String loaclFile, String checkFile) {

        // 下载请求，10个任务并发下载，启动断点续传。
        DownloadFileRequest downloadFileRequest = new DownloadFileRequest(bucketName, objectName);
        downloadFileRequest.setDownloadFile(loaclFile);
        downloadFileRequest.setPartSize(1024 * 1024);
        downloadFileRequest.setTaskNum(10);
        downloadFileRequest.setEnableCheckpoint(true);
        if (StringUtils.isNotEmpty(checkFile)) {
            downloadFileRequest.setCheckpointFile(checkFile);

        }
        // 下载文件。
        DownloadFileResult downloadRes = null;
        try {
            downloadRes = ossClient.downloadFile(downloadFileRequest);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        // 下载成功时，会返回文件元信息。
        ObjectMetadata objectMetadata = null;
        if (Objects.nonNull(downloadRes)) {
            objectMetadata = downloadRes.getObjectMetadata();
        }

        // 关闭OSSClient。
        ossClient.shutdown();

        return objectMetadata;

    }
}

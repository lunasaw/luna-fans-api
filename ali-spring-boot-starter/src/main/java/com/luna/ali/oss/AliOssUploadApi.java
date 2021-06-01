package com.luna.ali.oss;

import java.io.*;
import java.net.URL;

import com.luna.common.text.RandomStrUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.aliyun.oss.OSS;
import com.aliyun.oss.internal.OSSHeaders;
import com.aliyun.oss.model.*;
import com.luna.ali.config.AliOssConfigProperties;

/**
 * @Package: com.luna.ali.oss
 * @ClassName: AliOssUploadApi
 * @Author: luna
 * @CreateTime: 2020/8/21 22:23
 * @Description:
 */
public class AliOssUploadApi {

    private static final Logger log = LoggerFactory.getLogger(AliOssUploadApi.class);

    /**
     * 上传文件
     *
     * @param filePath 绝对路径
     * @param bucketName
     * @param imgFolder
     * @param configVale
     */
    public static String uploadByFilePath(String filePath, String bucketName, String imgFolder, String access,
        String type, AliOssConfigProperties configVale) {
        log.info("uploadByFilePath start filePath={},bucketName={},imgFolder={},access={},type={}", filePath,
            bucketName, imgFolder, access, type);
        // 创建PutObjectRequest对象。
        File file = new File(filePath);
        OSS ossClient = configVale.getOssClient(false);
        String fileName = file.getName();
        if (!imgFolder.endsWith("/")) {
            imgFolder = imgFolder + "/";
        }
        fileName = System.currentTimeMillis() + "_" + RandomStrUtil.generateNonceStrWithUUID() + "_" + fileName;
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, imgFolder + fileName, file);
        // 如果需要上传时设置存储类型与访问权限，请参考以下示例代码。
        ObjectMetadata metadata = getObjectMetadata(access, type);
        putObjectRequest.setMetadata(metadata);
        /** 上传文件 */
        PutObjectResult putObjectResult = ossClient.putObject(putObjectRequest);
        ossClient.shutdown();
        log.info("uploadByFilePath success putObjectResult={},filePath={},bucketName={},imgFolder={},access={},type={}",
            JSON.toJSONString(putObjectResult), filePath,
            bucketName, imgFolder, access, type);
        return configVale.getDomain() + "/" + imgFolder + file.getName();
    }

    /**
     * 流式上传
     * 使用ossClient.putObject上传数据流到OSS。
     *
     * 上传字符串
     *
     * @param content 内容
     * @param objectName 对象名
     * @param bucketName 存储空间
     * @param access 权限
     * @param type 存储类型
     * @param configVale
     */
    public static void uploadByString(String content, String objectName, String imgFolder, String bucketName,
        String access, String type, AliOssConfigProperties configVale) {
        OSS ossClient = configVale.getOssClient(false);

        if (!imgFolder.endsWith("/")) {
            imgFolder = imgFolder + "/";
        }

        PutObjectRequest putObjectRequest =
            new PutObjectRequest(bucketName, imgFolder + objectName, new ByteArrayInputStream(content.getBytes()));

        // 如果需要上传时设置存储类型与访问权限，请参考以下示例代码。
        ObjectMetadata metadata = getObjectMetadata(access, type);
        putObjectRequest.setMetadata(metadata);

        ossClient.putObject(putObjectRequest);

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    /**
     * 上传Byte数组
     *
     * @param content
     * @param objectName
     * @param bucketName
     * @param access
     * @param type
     * @param configVale
     * @return
     */
    public static void uploadByByteArray(byte[] content, String objectName, String imgFolder, String bucketName,
        String access,
        String type, AliOssConfigProperties configVale) {
        OSS ossClient = configVale.getOssClient(false);

        if (!imgFolder.endsWith("/")) {
            imgFolder = imgFolder + "/";
        }
        PutObjectRequest putObjectRequest =
            new PutObjectRequest(bucketName, imgFolder + objectName, new ByteArrayInputStream(content));

        // 如果需要上传时设置存储类型与访问权限，请参考以下示例代码。
        ObjectMetadata metadata = getObjectMetadata(access, type);
        putObjectRequest.setMetadata(metadata);

        ossClient.putObject(putObjectRequest);

        // 关闭OSSClient。
        ossClient.shutdown();

    }

    /**
     * 上传网络流
     *
     * @param url
     * @param objectName
     * @param bucketName
     * @param access
     * @param type
     * @param configVale
     * @return
     */
    public static void uploadByURLStream(URL url, String objectName, String imgFolder, String bucketName,
        String access,
        String type, AliOssConfigProperties configVale) throws IOException {
        OSS ossClient = configVale.getOssClient(false);

        if (!imgFolder.endsWith("/")) {
            imgFolder = imgFolder + "/";
        }
        InputStream inputStream = url.openStream();
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, imgFolder + objectName, inputStream);

        // 如果需要上传时设置存储类型与访问权限，请参考以下示例代码。
        ObjectMetadata metadata = getObjectMetadata(access, type);
        putObjectRequest.setMetadata(metadata);

        ossClient.putObject(putObjectRequest);

        // 关闭OSSClient。
        ossClient.shutdown();

    }

    /**
     * 上传文件流
     *
     * @param fileInputStream
     * @param objectName
     * @param bucketName
     * @param access
     * @param type
     * @param configVale
     * @return
     * @throws IOException
     */
    public static void uploadByFileStream(FileInputStream fileInputStream, String objectName, String imgFolder,
        String bucketName, String access,
        String type, AliOssConfigProperties configVale) {
        OSS ossClient = configVale.getOssClient(false);

        if (!imgFolder.endsWith("/")) {
            imgFolder = imgFolder + "/";
        }
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, imgFolder + objectName, fileInputStream);

        // 如果需要上传时设置存储类型与访问权限，请参考以下示例代码。
        ObjectMetadata metadata = getObjectMetadata(access, type);
        putObjectRequest.setMetadata(metadata);

        ossClient.putObject(putObjectRequest);

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    /**
     * 设置存储权限
     * 
     * @param access
     * @param type
     * @return
     */
    private static ObjectMetadata getObjectMetadata(String access, String type) {
        ObjectMetadata metadata = new ObjectMetadata();
        if (StringUtils.isNotEmpty(access)) {
            metadata.setObjectAcl(CannedAccessControlList.parse(access));
        }

        if (StringUtils.isNotEmpty(type)) {
            metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.parse(type));
        }
        return metadata;
    }
}

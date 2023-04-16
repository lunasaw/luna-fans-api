package com.luna.ali.oss;

import java.io.*;
import java.net.URL;
import java.util.Map;

import com.aliyun.oss.event.ProgressListener;
import com.google.common.collect.Maps;
import com.luna.common.date.DateUtils;
import com.luna.common.text.RandomStrUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.aliyun.oss.OSS;
import com.aliyun.oss.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @author Luna@win10
 * @date 2020/4/20 11:46
 */
@Component
public class AliOssUploadApi {

    @Autowired
    private AliOssClientSupport aliOssClientSupport;

    private static final Logger log = LoggerFactory.getLogger(AliOssUploadApi.class);

    /**
     * 上传文件
     *
     * @param fileName 文件路径
     * @param bucketName 桶名称
     * @param folder 文件网络路径
     * @return
     */
    public PutObjectResult uploadFile(String fileName, String bucketName, String folder) {
        Assert.notNull(bucketName, "存储空间名称不能为空");
        File file = new File(fileName);
        if (StringUtils.isNotEmpty(folder) && !folder.endsWith("/")) {
            folder += DateUtils.datePath() + "/";
        }
        String filePath = folder + "_" + RandomStrUtil.generateNonceStrWithUUID() + "_" + file.getName();
        return uploadFile(filePath, file, bucketName, folder, null, null);
    }

    /**
     * 上传文件
     *
     * @param fileName 文件路径
     * @param bucketName 桶名称
     * @param folder 文件网络路径
     * @param access 访问权限
     * @param type 存储类型
     * @return
     */
    public PutObjectResult uploadFile(String fileName, File file, String bucketName, String folder, String access, String type,
        Boolean enableListener, ProgressListener listener) {
        log.info("uploadFile::fileName = {}, file = {}, bucketName = {}, folder = {}, access = {}, type = {}, enableListener = {}", fileName, file,
            bucketName, folder, access, type, enableListener);
        if (StringUtils.isEmpty(type)) {
            type = StorageClass.Standard.toString();
        }

        if (StringUtils.isEmpty(access)) {
            // 默认公共读
            access = CannedAccessControlList.PublicRead.toString();
        }

        ObjectMetadata metadata = AliOssUtil.getObjectMetadata(access, type);
        PutObjectResult putObjectResult = uploadFile(fileName, file, bucketName, metadata, enableListener, listener);

        log.info(
            "uploadFile::fileName = {}, file = {}, bucketName = {}, folder = {}, access = {}, type = {}, enableListener = {}, putObjectResult = {}",
            fileName, file, bucketName, folder, access, type, enableListener, putObjectResult);
        return putObjectResult;
    }

    /**
     * 上传文件
     * 
     * @param fileName 文件路径
     * @param bucketName 桶名称
     * @param folder 文件网络路径
     * @param access 访问权限
     * @param type 存储类型
     * @return
     */
    public PutObjectResult uploadFile(String fileName, File file, String bucketName, String folder, String access, String type) {
        log.info("uploadFile::fileName = {}, bucketName = {}, folder = {}, access = {}, type = {}", fileName, bucketName, folder, access, type);

        if (StringUtils.isEmpty(type)) {
            type = StorageClass.Standard.toString();
        }

        if (StringUtils.isEmpty(access)) {
            // 默认公共读
            access = CannedAccessControlList.PublicRead.toString();
        }

        ObjectMetadata metadata = AliOssUtil.getObjectMetadata(access, type);
        PutObjectResult putObjectResult = uploadFile(fileName, file, bucketName, metadata);
        log.info("uploadFile::fileName = {}, putObjectResult = {}", fileName, JSON.toJSONString(putObjectResult));
        return putObjectResult;
    }

    /**
     * 上传文件
     *
     * @param objectName 文件名称
     * @param file 文件
     * @param bucketName 桶名称
     * @param metadata 权限
     */
    public PutObjectResult uploadFile(String objectName, File file, String bucketName, ObjectMetadata metadata) {
        return uploadFile(objectName, file, bucketName, metadata, StringUtils.EMPTY);
    }

    /**
     * 上传文件
     *
     * @param objectName 文件名称
     * @param file 文件
     * @param bucketName 桶名称
     * @param metadata 权限
     * @param callbackUrl
     */
    public PutObjectResult uploadFile(String objectName, File file, String bucketName, ObjectMetadata metadata, String callbackUrl) {
        return uploadFile(objectName, file, bucketName, metadata, callbackUrl, StringUtils.EMPTY);
    }

    /**
     * 上传文件
     *
     * @param objectName 文件名称
     * @param file 文件
     * @param bucketName 桶名称
     * @param metadata 权限
     * @param callbackUrl 回调URL
     * @param callbackHost （可选）设置回调请求消息头中Host的值，即您的服务器配置Host的值。
     */
    public PutObjectResult uploadFile(String objectName, File file, String bucketName, ObjectMetadata metadata, String callbackUrl,
        String callbackHost) {
        return uploadFile(objectName, file, bucketName, metadata, callbackUrl, callbackHost, StringUtils.EMPTY);
    }

    /**
     * 上传文件
     *
     * @param objectName 文件名称
     * @param file 文件
     * @param bucketName 桶名称
     * @param metadata 权限
     * @param callbackUrl 回调URL
     * @param callbackHost （可选）设置回调请求消息头中Host的值，即您的服务器配置Host的值。
     * @param callbackBody 设置发起回调时请求body的值 {\"mimeType\":${mimeType},\"size\":${size}}
     */
    public PutObjectResult uploadFile(String objectName, File file, String bucketName, ObjectMetadata metadata, String callbackUrl,
        String callbackHost, String callbackBody) {
        return uploadFile(objectName, file, bucketName, metadata, callbackUrl, callbackHost, callbackBody, Maps.newHashMap(), false, null);
    }

    /**
     * 上传文件
     *
     * @param objectName 文件名称
     * @param file 文件
     * @param bucketName 桶名称
     * @param metadata 权限
     * @param enableListener 是否监听
     * @param listener 监听器
     */
    public PutObjectResult uploadFile(String objectName, File file, String bucketName, ObjectMetadata metadata, Boolean enableListener,
        ProgressListener listener) {
        return uploadFile(objectName, file, bucketName, metadata, StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY, Maps.newHashMap(),
            enableListener, listener);
    }

    /**
     * 上传文件
     *
     * @param objectName 文件名称
     * @param file 文件
     * @param bucketName 桶名称
     * @param metadata 权限
     * @param callbackUrl 回调URL
     * @param callbackHost （可选）设置回调请求消息头中Host的值，即您的服务器配置Host的值。
     * @param callbackBody 设置发起回调时请求body的值 {\"mimeType\":${mimeType},\"size\":${size}}
     * @param callbackMap 设置发起回调请求的自定义参数，由Key和Value组成，Key必须以x:开始。
     * @param enableListener 是否监听
     * @param listener 监听器
     */
    public PutObjectResult uploadFile(String objectName, File file, String bucketName, ObjectMetadata metadata, String callbackUrl,
        String callbackHost, String callbackBody, Map<String, String> callbackMap, Boolean enableListener, ProgressListener listener) {

        log.info(
            "uploadFile::objectName = {}, file = {}, bucketName = {}, metadata = {}, callbackUrl = {}, callbackHost = {}, callbackBody = {}, callbackMap = {}, enableListener = {}, listener = {}",
            objectName, file, bucketName, metadata, callbackUrl, callbackHost, callbackBody, callbackMap, enableListener, listener);

        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, file);

        if (StringUtils.isNotEmpty(callbackUrl)) {
            Callback callback = getCallback(callbackUrl, callbackHost, callbackBody, callbackMap);
            putObjectRequest.setCallback(callback);
        }
        putObjectRequest.setMetadata(metadata);
        if (enableListener) {
            putObjectRequest.withProgressListener(listener);
        }
        return doRequest(putObjectRequest);
    }

    public Callback getCallback(String callbackUrl, String callbackHost, String callbackBody, Map<String, String> callbackMap) {
        Assert.notNull(callbackUrl, "回调路径不能为空");
        Callback callback = new Callback();
        callback.setCallbackUrl(callbackUrl);
        if (StringUtils.isNotEmpty(callbackHost)) {
            callback.setCallbackHost(callbackHost);
        }
        callback.setCallbackBody(callbackBody);
        // 设置发起回调请求的Content-Type。
        callback.setCalbackBodyType(Callback.CalbackBodyType.JSON);
        callbackMap.forEach(callback::addCallbackVar);
        return callback;
    }

    /**
     * 流式上传
     *
     * @param content 内容
     * @param objectName 桶名称
     * @param metadata 权限
     */
    public PutObjectRequest uploadStream(String content, String objectName, String bucketName, ObjectMetadata metadata) {
        PutObjectRequest putObjectRequest =
            new PutObjectRequest(bucketName, objectName, new ByteArrayInputStream(content.getBytes()));

        putObjectRequest.setMetadata(metadata);

        return putObjectRequest;
    }

    /**
     * 字节上传
     *
     * @param content 内容
     * @param objectName 桶名称
     * @param metadata 权限
     */
    public PutObjectResult uploadByte(byte[] content, String objectName, String bucketName, ObjectMetadata metadata) {
        PutObjectRequest putObjectRequest =
            new PutObjectRequest(bucketName, objectName, new ByteArrayInputStream(content));

        putObjectRequest.setMetadata(metadata);
        return doRequest(putObjectRequest);
    }

    public PutObjectResult doRequest(PutObjectRequest putObjectRequest) {
        try {
            return aliOssClientSupport.getInstanceClient().putObject(putObjectRequest);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 网络流上传
     *
     * @param content 内容
     * @param objectName 桶名称
     * @param metadata 权限
     */
    public PutObjectResult uploadURL(URL content, String objectName, String bucketName, ObjectMetadata metadata) {
        try {
            InputStream inputStream = content.openStream();
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, inputStream);
            putObjectRequest.setMetadata(metadata);
            return doRequest(putObjectRequest);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 文件流式上传
     *
     * @param content 内容
     * @param objectName 桶名称
     * @param metadata 权限
     */
    public PutObjectResult uploadFileStream(FileInputStream content, String objectName, String bucketName, ObjectMetadata metadata) {
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, content);

        putObjectRequest.setMetadata(metadata);
        return doRequest(putObjectRequest);
    }

}

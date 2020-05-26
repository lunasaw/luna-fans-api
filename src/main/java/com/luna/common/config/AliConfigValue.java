package com.luna.common.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Luna@win10
 * @date 2020/5/6 21:09
 */
@Component
@ConfigurationProperties(prefix = "luna.ali")
public class AliConfigValue {

    private String ossId;

    private String ossKey;

    private String bucketName;

    private String host;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    /** 创建OSSClient实例 */
    private OSS ossClient;

    public OSS getOssClient() {
        if (ossClient == null) {
            this.ossClient = new OSSClientBuilder().build(host, ossId, ossKey);
        }
        return ossClient;
    }

    public void setOssClient(OSS ossClient) {
        this.ossClient = ossClient;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getOssId() {
        return ossId;
    }

    public void setOssId(String ossId) {
        this.ossId = ossId;
    }

    public String getOssKey() {
        return ossKey;
    }

    public void setOssKey(String ossKey) {
        this.ossKey = ossKey;
    }
}

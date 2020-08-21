package com.luna.ali.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

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

    private String domain;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    /** 创建OSSClient实例 */
    private OSS ossClient;

    public OSS getOssClient(Boolean isCname) {
        if (isCname) {
            ClientBuilderConfiguration conf = new ClientBuilderConfiguration();
            // 设置是否支持CNAME。CNAME是指将自定义域名绑定到存储空间上。
            conf.setSupportCname(true);
            return new OSSClientBuilder().build(domain, ossId, ossKey, conf);
        }
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

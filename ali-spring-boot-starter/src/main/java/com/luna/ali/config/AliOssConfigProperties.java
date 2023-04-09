package com.luna.ali.config;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

import javax.annotation.PostConstruct;

/**
 * @author Luna@win10
 * @date 2020/5/6 21:09
 */
@ConfigurationProperties(prefix = "spring.ali.oss")
@Data
public class AliOssConfigProperties {

    private Boolean enable;

    private String  accessKey;

    private String  secretKey;

    private String  bucketName;

    /**
     * endpoint 拼接 http 开头
     */
    private String  endpoint;
    /**
     * 自定义域名
     */
    private String  domain;

    /**
     * 回调路径
     */
    private String  callbackUrl;

    /**
     * 回调请求头校验
     */
    private String  callbackHost;

    /**
     * 是否开启自定义域名
     */
    private Boolean enableCname;

    /**
     * 创建OSSClient实例
     */
    private OSS     client;

    @PostConstruct
    public OSS getInstanceClient() {
        if (null == enableCname) {
            return getInstanceClient(false);
        }
        return getInstanceClient(true);
    }

    public OSS getInstanceClient(Boolean isCname) {
        if (isCname && StringUtils.isNotEmpty(domain)) {
            ClientBuilderConfiguration conf = new ClientBuilderConfiguration();
            // 设置是否支持CNAME。CNAME是指将自定义域名绑定到存储空间上。
            conf.setSupportCname(true);
            return new OSSClientBuilder().build(domain, accessKey, secretKey, conf);
        }
        if (client == null) {
            return new OSSClientBuilder().build(endpoint, accessKey, secretKey);
        }
        return client;
    }
}

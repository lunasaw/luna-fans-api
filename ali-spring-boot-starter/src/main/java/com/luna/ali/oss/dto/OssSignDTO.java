package com.luna.ali.oss.dto;

import lombok.Data;

/**
 * @author luna
 * 2022/5/23
 */
@Data
public class OssSignDTO {

    /**
     * 策略
     */
    private String policy;

    /**
     * 签名
     */
    private String signature;

    /**
     * 过期时间
     */
    private String expire;

    /**
     * host
     */
    private String host;
    /**
     * 文件名
     */
    private String objectName;

    /**
     * 目录
     */
    private String dir;

    /**
     * accessKey
     */
    private String accessKey;
    /**
     * 回调路径
     */
    private String callbackUrl;
    /**
     * 是否自定义域名
     */
    private String enableCname;
    /**
     * 域名
     */
    private String domain;
    /**
     * 回调参数
     */
    private String callback;
}

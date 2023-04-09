package com.luna.baidu.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author luna@mac
 * @className BaiduProperties.java
 * @description TODO 参数设置
 * @createTime 2021年03月27日 13:02:00
 */
@ConfigurationProperties(prefix = "spring.baidu")
public class BaiduProperties {

    private String          appKey;

    private String          secretKey;

    private String          appId;

    private String          baiduKey;

    private String          projectId;

    private String          mapKey;


    public String getMapKey() {
        return mapKey;
    }

    public void setMapKey(String mapKey) {
        this.mapKey = mapKey;
    }

    public String getBaiduKey() {
        return baiduKey;
    }

    public void setBaiduKey(String baiduKey) {
        this.baiduKey = baiduKey;
    }

    public String getAppKey() {
        return appKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

}

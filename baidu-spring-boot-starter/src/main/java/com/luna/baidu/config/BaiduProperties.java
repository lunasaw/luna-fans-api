package com.luna.baidu.config;

import com.baidu.aip.bodyanalysis.AipBodyAnalysis;
import com.baidu.aip.speech.AipSpeech;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author luna@mac
 * @className BaiduProperties.java
 * @description TODO 参数设置
 * @createTime 2021年03月27日 13:02:00
 */
@ConfigurationProperties(prefix = "luna.baidu")
public class BaiduProperties {

    private String          appKey;

    private String          secretKey;

    private String          appId;

    private String          baiduKey;

    private String          projectId;

    private String          mapKey;

    /** 语音识别 */
    private AipSpeech       aipSpeech;
    /** 人体识别 */
    private AipBodyAnalysis client;

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

    public AipSpeech getAipSpeech() {
        if (aipSpeech == null) {
            this.aipSpeech = new AipSpeech(this.appId, this.appKey, this.secretKey);
        }
        return aipSpeech;
    }

    public AipBodyAnalysis getClient() {
        if (client == null) {
            this.client = new AipBodyAnalysis(this.appId, this.appKey, this.secretKey);
        }
        return client;
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

    public void setAipSpeech(AipSpeech aipSpeech) {
        this.aipSpeech = aipSpeech;
    }

    public void setClient(AipBodyAnalysis client) {
        this.client = client;
    }

}

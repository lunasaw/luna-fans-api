package com.luna.common.config;

import com.baidu.aip.bodyanalysis.AipBodyAnalysis;
import com.baidu.aip.speech.AipSpeech;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "luna.baidu")
public class BaiduConfigValue {

    private String          appKey;

    private String          secretKey;

    private String          appId;

    /** 语音识别 */
    private AipSpeech       aipSpeech;
    /** 人体识别 */
    private AipBodyAnalysis client;

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
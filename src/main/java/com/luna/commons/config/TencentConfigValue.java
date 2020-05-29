package com.luna.commons.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "luna.tencent")
public class TencentConfigValue {

    private String secretid;

    private String secretKey;

    private String skyEyeSecretid;

    private String skyEyeSecretkey;

    public String getSecretid() {
        return secretid;
    }

    public void setSecretid(String secretid) {
        this.secretid = secretid;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getSkyEyeSecretid() {
        return skyEyeSecretid;
    }

    public void setSkyEyeSecretid(String skyEyeSecretid) {
        this.skyEyeSecretid = skyEyeSecretid;
    }

    public String getSkyEyeSecretkey() {
        return skyEyeSecretkey;
    }

    public void setSkyEyeSecretkey(String skyEyeSecretkey) {
        this.skyEyeSecretkey = skyEyeSecretkey;
    }
}
package com.luna.tencent.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author luna
 */
@Component
@ConfigurationProperties(prefix = "spring.tencent")
@Data
public class TencentApiConfigProperties {

    private Boolean enable;

    private String secretId;

    private String secretKey;

    private String skyEyeSecretId;

    private String skyEyeSecretKey;

    public String getSecretId() {
        return secretId;
    }

    public void setSecretId(String secretId) {
        this.secretId = secretId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getSkyEyeSecretId() {
        return skyEyeSecretId;
    }

    public void setSkyEyeSecretId(String skyEyeSecretId) {
        this.skyEyeSecretId = skyEyeSecretId;
    }

    public String getSkyEyeSecretKey() {
        return skyEyeSecretKey;
    }

    public void setSkyEyeSecretKey(String skyEyeSecretKey) {
        this.skyEyeSecretKey = skyEyeSecretKey;
    }
}
package com.luna.api.xfyun.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * @Package: com.luna.api.smMs
 * @ClassName: SmMsConfigValue
 * @Author: luna
 * @CreateTime: 2020/7/27 10:12
 * @Description:
 */
@Component
@ConfigurationProperties(prefix = "spring.xunfei")
@Data
public class XunfeiProperties {

    private Boolean enable;

    private String apikey;
    private String appId;
    private String apiSecret;

}

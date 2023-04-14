package com.luna.api.smms.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Package: com.luna.api.smMs
 * @ClassName: SmMsConfigValue
 * @Author: luna
 * @CreateTime: 2020/7/27 10:12
 * @Description:
 */
@Component
@ConfigurationProperties(prefix = "spring.smms")
@Data
public class SmMsProperties {

    private Boolean enable;

    /** 用户名 */
    private String username;
    /** 密码 */
    private String password;
    /** 网关密钥 */
    private String authorizationCode;

}

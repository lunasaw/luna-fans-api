package com.luna.api.smms.config;

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
@ConfigurationProperties(prefix = "luna.smms")
public class SmMsConfigValue {

    // 用户名
    private String username;
    // 密码
    private String password;
    // 网关密钥
    private String authorizationCode;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }
}

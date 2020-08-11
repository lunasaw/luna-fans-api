package com.luna.message.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;

/**
 * @Package: com.luna.message.config
 * @ClassName: JavaMailConfigValue
 * @Author: luna
 * @CreateTime: 2020/8/11 12:40
 * @Description:
 */
@Component
@ConfigurationProperties(prefix = "luna.mail")
public class JavaMailConfigValue {

    /** 邮箱服务器地址 */
    @NotEmpty
    private String host        = "";
    /** 发送邮件的端口 */
    @NotEmpty
    private String port        = "";
    /** 是否需要进行身份验证,视调用的邮箱而定，比方说QQ邮箱就需要，否则就会发送失败 */
    @NotEmpty
    private String auth        = "";
    /** 传输协议 */
    @NotEmpty
    private String protocol    = "";
    /** 发件人邮箱 */
    @NotEmpty
    private String mailFrom    = "";
    @NotEmpty
    /** 发件人邮箱用户名 */
    private String username    = "";
    @NotEmpty
    /** 发件人邮箱密码 */
    private String password    = "";
    @NotEmpty
    /** 是否开启debug */
    private String mailDebug   = "false";
    @NotEmpty
    /** 文本类型 */
    private String contentType = "text/html;charset=UTF-8";

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getMailFrom() {
        return mailFrom;
    }

    public void setMailFrom(String mailFrom) {
        this.mailFrom = mailFrom;
    }

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

    public String getMailDebug() {
        return mailDebug;
    }

    public void setMailDebug(String mailDebug) {
        this.mailDebug = mailDebug;
    }
}

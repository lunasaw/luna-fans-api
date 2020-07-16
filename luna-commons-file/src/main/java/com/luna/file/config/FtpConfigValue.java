package com.luna.file.config;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Luna@win10
 * @date 2020/5/16 11:54
 */
@ConfigurationProperties(prefix = "luna.ftp")
@Component
public class FtpConfigValue {

    public String    host;

    /** ftp端口号 */
    public int       port = 21;

    public String    username;

    public String    password;

    /** 请求图片路径 */
    public String    picture;

    /** luna路径 */
    public String    basePath;

    /** 音乐 */
    public String    voice;

    /** 配置文件路径 */
    public String    conf;

    /** 导入到本地临时路径 */
    public String    local;

    public FTPClient ftpClient;

    public FTPClient getFtpClient() {
        if (ftpClient == null) {
            this.ftpClient = new FTPClient();
        }
        return ftpClient;
    }

    public void setFtpClient(FTPClient ftpClient) {
        this.ftpClient = ftpClient;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }

    public String getConf() {
        return conf;
    }

    public void setConf(String conf) {
        this.conf = conf;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
}

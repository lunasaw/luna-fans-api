package com.luna.file.config;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

/**
 * @Package: com.luna.file.httpd
 * @ClassName: UserAuth
 * @Author: luna
 * @CreateTime: 2020/11/13 19:28
 * @Description:
 */
@ConfigurationProperties(prefix = "luna.http")
@Component
public class HttpUserAuthConfigValue {

    private String     username;
    private String     password;
    /**
     * 客户端
     */
    private Client     client;

    private HttpClient httpClient;

    public HttpClient getHttpClient() {
        if (httpClient == null) {
            this.httpClient = new HttpClient();
            // 设置用户名密码认证形式
            Credentials creds = new UsernamePasswordCredentials(username, password);
            httpClient.getState().setCredentials(AuthScope.ANY, creds);
            return httpClient;
        }
        return httpClient;
    }

    public HttpClient getHttpClient(String username, String password) {
        if (httpClient == null) {
            this.httpClient = new HttpClient();
            // 设置用户名密码认证形式
            Credentials creds = new UsernamePasswordCredentials(username, password);
            httpClient.getState().setCredentials(AuthScope.ANY, creds);
            return httpClient;
        }
        return httpClient;
    }

    public HttpUserAuthConfigValue setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
        return this;
    }

    public Client getClient() {
        if (client == null) {
            this.client = Client.create();
            client.addFilter(new HTTPBasicAuthFilter(username, password));
            return client;
        }
        return client;
    }

    public HttpUserAuthConfigValue setClient(Client client) {
        this.client = client;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public HttpUserAuthConfigValue setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public HttpUserAuthConfigValue setPassword(String password) {
        this.password = password;
        return this;
    }
}

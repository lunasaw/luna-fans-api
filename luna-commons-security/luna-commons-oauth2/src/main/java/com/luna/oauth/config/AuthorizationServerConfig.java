package com.luna.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * @Package: com.luna.oauth.config
 * @ClassName: AuthorizationServerConfig
 * @Author: luna
 * @CreateTime: 2020/9/30 14:03
 * @Description: 开启授权服务器
 * 客户端 -> 授权服务器 拿token -> 带token 资源服务器
 *
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
            .inMemory()
            // 客户端ID
            .withClient("client")
            // 密钥
            .secret(passwordEncoder.encode("luna"))
            // 重定向地址
            .redirectUris("https://luna_nov.gitee.io/blog/")
            // 范围
            .scopes("all")
            /**
             * 授权类型
             * 
             * authorization_code 授权码模式
             */
            .authorizedGrantTypes("authorization_code");
    }
}

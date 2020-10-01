package com.luna.oauth.config;

import com.google.common.collect.Lists;
import com.luna.jwt.constant.JwtConstants;
import com.luna.oauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.ArrayList;

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
    private PasswordEncoder         passwordEncoder;

    /**
     * 身份验证管理器
     */
    @Autowired
    private AuthenticationManager   authenticationManager;

    /**
     * 自定义登录处理
     */
    @Autowired
    private UserService             userService;

    /**
     * 存储在redis
     */
    @Autowired
    @Qualifier("redisTokenStore")
    private TokenStore              redisTokenStore;

    @Autowired
    @Qualifier("jwtTokenStore")
    private TokenStore              jwtTokenStore;

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired
    private JwtTokenEnhancer        jwtTokenEnhancer;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 设置jwt增强内容
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        ArrayList<TokenEnhancer> delegates = Lists.newArrayList();
        delegates.add(jwtTokenEnhancer);
        delegates.add(jwtAccessTokenConverter);
        tokenEnhancerChain.setTokenEnhancers(delegates);
        endpoints
            .authenticationManager(authenticationManager)
            .userDetailsService(userService)
            // .tokenStore(tokenStore)
            .tokenStore(jwtTokenStore)
            .accessTokenConverter(jwtAccessTokenConverter)
            .tokenEnhancer(tokenEnhancerChain);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
            .inMemory()
            // 客户端ID
            .withClient("client")
            // 验证权限
            .authorities("admin")
            // 密钥
            .secret(passwordEncoder.encode(JwtConstants.JWT_KEY))
            // .secret("luna")
            // 授权时间
            .accessTokenValiditySeconds(60)
            // 刷新令牌过期时间
            .refreshTokenValiditySeconds(300)
            // 重定向地址
            .redirectUris("http://localhost:8091/client/login")
            // 范围
            .scopes("all")
            // 自动授权
            .autoApprove(true)
            /**
             * 授权类型
             * 支持多种类型
             * authorization_code 授权码模式
             * password 密码模式
             * refresh_token 刷新令牌
             */
            .authorizedGrantTypes("authorization_code", "password", "refresh_token");
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 单点登录配置密钥
        security.tokenKeyAccess("isAuthenticated()");
    }
}

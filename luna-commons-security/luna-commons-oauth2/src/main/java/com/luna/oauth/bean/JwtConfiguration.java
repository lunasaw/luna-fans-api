package com.luna.oauth.bean;

import com.luna.jwt.constant.JwtConstants;
import com.luna.oauth.config.JwtTokenEnhancer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @Package: com.luna.oauth.bean
 * @ClassName: JwtConfiguration
 * @Author: luna
 * @CreateTime: 2020/10/1 15:51
 * @Description:
 */
@Configuration
public class JwtConfiguration {

    @Bean(name = "jwtTokenStore")
    @Primary
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey(JwtConstants.JWT_KEY);
        return jwtAccessTokenConverter;
    }

    @Bean
    public JwtTokenEnhancer jwtTokenEnhancer() {
        return new JwtTokenEnhancer();
    }
}

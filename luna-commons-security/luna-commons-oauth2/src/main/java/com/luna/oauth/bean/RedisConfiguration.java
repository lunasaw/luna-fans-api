package com.luna.oauth.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @Package: com.luna.oauth.bean
 * @ClassName: RedisConfig
 * @Author: luna
 * @CreateTime: 2020/9/30 17:52
 * @Description:
 */
@Configuration
@ComponentScan("com.luna.redis")
public class RedisConfiguration {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Bean("redisTokenStore")
    public TokenStore redisTokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }
}

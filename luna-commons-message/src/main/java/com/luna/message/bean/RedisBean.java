package com.luna.message.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.luna.redis.config.RedisConfig;
import com.luna.redis.util.RedisKeyUtil;
import com.luna.redis.util.RedisOpsUtil;

/**
 * @Package: com.luna.message.bean
 * @ClassName: RedisBean
 * @Author: luna
 * @CreateTime: 2020/8/31 16:26
 * @Description:
 */
@Configuration
public class RedisBean {

    @Bean
    public RedisOpsUtil redisOpsUtil() {
        return new RedisOpsUtil();
    }

    @Bean
    public RedisKeyUtil redisKeyUtil() {
        return new RedisKeyUtil();
    }

    @Bean
    public RedisConfig redisConfig() {
        return new RedisConfig();
    }
}

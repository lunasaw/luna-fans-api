package com.luna.api.config;

import com.luna.api.smms.config.SmMsProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author luna@mac
 * @className ApiAutoConfiguration.java
 * @description TODO
 * @createTime 2021年03月28日 16:21:00
 */
@Configuration
@EnableConfigurationProperties(SmMsProperties.class)
@ConditionalOnProperty(prefix = "luna.apis.smms", name = "enable", havingValue = "true")
public class ApiAutoConfiguration {

    @Autowired
    private final SmMsProperties smMsProperties;

    public ApiAutoConfiguration(final SmMsProperties smMsProperties) {
        this.smMsProperties = smMsProperties;
    }
}

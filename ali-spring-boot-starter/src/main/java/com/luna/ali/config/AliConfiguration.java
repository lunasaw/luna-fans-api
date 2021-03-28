package com.luna.ali.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author luna@mac
 * @className AliConfiguration.java
 * @description TODO
 * @createTime 2021年03月28日 13:01:00
 */
@Configuration
@ConditionalOnProperty(prefix = "luna.ali", name = "enable", havingValue = "true")
@EnableConfigurationProperties({AliConfigValue.class, AlipayConfigValue.class})
public class AliConfiguration {

    private final AliConfigValue    aliConfigValue;

    private final AlipayConfigValue alipayConfigValue;

    public AliConfiguration(AliConfigValue aliConfigValue, AlipayConfigValue alipayConfigValue) {
        this.aliConfigValue = aliConfigValue;
        this.alipayConfigValue = alipayConfigValue;
    }
}

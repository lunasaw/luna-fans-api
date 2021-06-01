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
@EnableConfigurationProperties({AliOssConfigProperties.class, AlipayConfigProperties.class})
public class AliConfiguration {

    private final AliOssConfigProperties aliOssConfigProperties;

    private final AlipayConfigProperties alipayConfigProperties;

    public AliConfiguration(AliOssConfigProperties aliOssConfigProperties,
        AlipayConfigProperties alipayConfigProperties) {
        this.aliOssConfigProperties = aliOssConfigProperties;
        this.alipayConfigProperties = alipayConfigProperties;
    }
}

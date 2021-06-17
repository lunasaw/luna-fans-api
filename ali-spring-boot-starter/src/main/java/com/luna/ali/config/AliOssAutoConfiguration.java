package com.luna.ali.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author luna@mac
 */
@Configuration
@ConditionalOnProperty(prefix = "luna.ali", name = "ossId")
@EnableConfigurationProperties({AliOssConfigProperties.class})
public class AliOssAutoConfiguration {

    private final AliOssConfigProperties aliOssConfigProperties;

    public AliOssAutoConfiguration(AliOssConfigProperties aliOssConfigProperties) {
        this.aliOssConfigProperties = aliOssConfigProperties;
    }
}

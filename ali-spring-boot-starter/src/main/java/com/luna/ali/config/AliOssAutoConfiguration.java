package com.luna.ali.config;

import com.luna.ali.oss.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author luna@mac
 */
@Configuration
@ConditionalOnProperty(prefix = "spring.ali", name = "enable", havingValue = "true")
@EnableConfigurationProperties({AliConfigProperties.class})
@ComponentScan("com.luna.ali")
public class AliOssAutoConfiguration {

    @Autowired
    private final AliConfigProperties aliConfigProperties;

    public AliOssAutoConfiguration(final AliConfigProperties aliConfigProperties) {
        this.aliConfigProperties = aliConfigProperties;
    }
}

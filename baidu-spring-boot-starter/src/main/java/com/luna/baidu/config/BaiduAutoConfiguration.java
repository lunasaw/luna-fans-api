package com.luna.baidu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author luna@mac
 * @className BaiduAutoConfiguration.java
 * @description TODO
 * @createTime 2021年03月27日 12:58:00
 */
@Configuration
@ConditionalOnProperty(prefix = "spring.baidu", name = "enable", havingValue = "true")
@EnableConfigurationProperties(BaiduProperties.class)
public class BaiduAutoConfiguration {

    @Autowired
    private final BaiduProperties baiduProperties;

    public BaiduAutoConfiguration(final BaiduProperties baiduProperties) {
        this.baiduProperties = baiduProperties;
    }

    @Bean
    @ConditionalOnMissingBean
    public BaiduKeyGenerate getBaiduKey() {
        return new BaiduKeyGenerate(baiduProperties);
    }
}

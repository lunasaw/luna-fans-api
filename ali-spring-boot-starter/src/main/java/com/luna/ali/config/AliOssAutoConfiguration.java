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
@EnableConfigurationProperties({AliOssConfigProperties.class})
@ComponentScan("com.luna.ali.oss")
public class AliOssAutoConfiguration {

    @Autowired
    private final AliOssConfigProperties aliOssConfigProperties;

    public AliOssAutoConfiguration(final AliOssConfigProperties aliOssConfigProperties) {
        this.aliOssConfigProperties = aliOssConfigProperties;
    }

    @Bean
    @ConditionalOnMissingBean
    public AliOssUploadApi aliOssUploadApi() {
        return new AliOssUploadApi(aliOssConfigProperties.getInstanceClient());
    }

    @Bean
    @ConditionalOnMissingBean
    public AliOssDownloadApi aliOssDownloadApi() {
        return new AliOssDownloadApi(aliOssConfigProperties.getInstanceClient());
    }

    @Bean
    @ConditionalOnMissingBean
    public AliOssBucketApi aliOssBucketApi() {
        return new AliOssBucketApi(aliOssConfigProperties.getInstanceClient());
    }

    @Bean
    @ConditionalOnMissingBean
    public AliOssUploadGoOnApi aliOssUploadGoOnApi() {
        return new AliOssUploadGoOnApi(aliOssConfigProperties.getInstanceClient());
    }

    @Bean
    @ConditionalOnMissingBean
    public AliOssWebApi aliOssWebApi() {
        return new AliOssWebApi(aliOssConfigProperties.getInstanceClient());
    }
}

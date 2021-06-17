package com.luna.ali.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author luna@mac
 */
@Configuration
@ConditionalOnProperty(prefix = "luna.ali", name = "appId")
@EnableConfigurationProperties({AlipayConfigProperties.class})
public class AliPayAutoConfiguration {

    private final AlipayConfigProperties alipayConfigProperties;

    public AliPayAutoConfiguration(AlipayConfigProperties alipayConfigProperties) {
        this.alipayConfigProperties = alipayConfigProperties;
    }
}

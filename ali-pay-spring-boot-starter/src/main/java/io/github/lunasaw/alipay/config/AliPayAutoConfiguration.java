package io.github.lunasaw.alipay.config;

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
@ConditionalOnProperty(prefix = "spring.alipay", name = "enable", havingValue = "true")
@EnableConfigurationProperties({AlipayConfigProperties.class})
@ComponentScan("io.github.lunasaw.alipay")
public class AliPayAutoConfiguration {

    @Autowired
    private final AlipayConfigProperties alipayConfigProperties;

    public AliPayAutoConfiguration(final AlipayConfigProperties alipayConfigProperties) {
        this.alipayConfigProperties = alipayConfigProperties;
    }

}

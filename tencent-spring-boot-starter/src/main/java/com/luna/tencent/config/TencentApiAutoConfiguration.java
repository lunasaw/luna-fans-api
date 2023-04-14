package com.luna.tencent.config;

import com.luna.tencent.properties.TencentApiConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author luna@mac
 * @className TencentConfiguration.java
 * @description TODO
 * @createTime 2021年03月27日 17:06:00
 */
@Configuration
@ConditionalOnProperty(prefix = "spring.tencent", name = "secretId")
@EnableConfigurationProperties({TencentApiConfigProperties.class})
public class TencentApiAutoConfiguration {

    @Autowired
    private TencentApiConfigProperties   tencentApiConfigProperties;

    public TencentApiAutoConfiguration(TencentApiConfigProperties tencentApiConfigProperties) {
        this.tencentApiConfigProperties = tencentApiConfigProperties;
    }

}

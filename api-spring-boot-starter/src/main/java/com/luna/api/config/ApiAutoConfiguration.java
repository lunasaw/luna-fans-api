package com.luna.api.config;

import com.luna.api.smms.config.SmMsProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author luna@mac
 */
@Configuration
@EnableConfigurationProperties(SmMsProperties.class)
public class ApiAutoConfiguration {

    private SmMsProperties smMsProperties;

    @Autowired
    public void setSmMsProperties(SmMsProperties smMsProperties) {
        this.smMsProperties = smMsProperties;
    }

}

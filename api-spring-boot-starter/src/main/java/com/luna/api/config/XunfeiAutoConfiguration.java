package com.luna.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

import com.luna.api.xfyun.config.XunfeiProperties;

/**
 * @author luna
 * @date 2024/6/20
 */
@AutoConfiguration
@EnableConfigurationProperties({XunfeiAutoConfiguration.class})
@ComponentScans(value = {@ComponentScan(value = "com.luna.api.xfyun.*")})
@ConditionalOnClass(value = DataSourceAutoConfiguration.class)
public class XunfeiAutoConfiguration {

    private XunfeiProperties xunfeiProperties;

    public XunfeiProperties getXunfeiProperties() {
        return xunfeiProperties;
    }

    @Autowired
    public void setXunfeiProperties(XunfeiProperties xunfeiProperties) {
        this.xunfeiProperties = xunfeiProperties;
    }
}

package com.luna.api.config;

import com.luna.api.email.service.MessageService;
import com.luna.api.smms.config.SmMsProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;

@AutoConfiguration
@EnableConfigurationProperties({SmMsProperties.class, MailSendProperties.class})
@ComponentScans(value = {@ComponentScan(value = "com.luna.api.email.*")})
@MapperScan(basePackages = "com.luna.api.email.mapper")
@ConditionalOnClass(value = DataSourceAutoConfiguration.class)
/**
 * @author luna@mac
 */
public class ApiAutoConfiguration {

    private SmMsProperties     smMsProperties;

    private MailSendProperties mailSendProperties;

    @Autowired
    public void setSmMsProperties(SmMsProperties smMsProperties) {
        this.smMsProperties = smMsProperties;
    }

    @Autowired
    public void setMailSendProperties(MailSendProperties mailSendProperties) {
        this.mailSendProperties = mailSendProperties;
    }

}

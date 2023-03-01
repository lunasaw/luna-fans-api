package com.luna.api.config;

import com.luna.api.email.service.MessageService;
import com.luna.api.smms.config.SmMsProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author luna@mac
 */
@Configuration
@EnableConfigurationProperties({SmMsProperties.class, MailSendProperties.class})
public class ApiAutoConfiguration {

    private SmMsProperties smMsProperties;

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

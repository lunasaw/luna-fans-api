package com.luna.api.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author chenzhangyue
 * 2023/3/1
 */
@ConfigurationProperties(prefix = "spring.mail")
@Data
public class MailSendProperties {

    /** 发送方昵称 */
    private String nick;

    public void setNick(String nick) {
        this.nick = nick;
    }
}

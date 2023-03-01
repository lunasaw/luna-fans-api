package com.luna.api.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author chenzhangyue
 * 2023/3/1
 */
@ConfigurationProperties(prefix = "luna.mail")
@Data
public class MailSendProperties {


    /** 发送方邮件地址 */
    private String              username;

    /** 发送方邮件昵称 */
    private String              userNick;
}

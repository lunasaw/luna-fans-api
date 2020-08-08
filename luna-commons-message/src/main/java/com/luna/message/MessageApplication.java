package com.luna.message;

import com.luna.message.config.TencentConfigValue;
import com.luna.message.config.TencentSmsConfigValue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @Package: com.luna.message
 * @ClassName: MessageApplication
 * @Author: luna
 * @CreateTime: 2020/7/16 16:54
 * @Description:
 */
@SpringBootApplication
public class MessageApplication {
    public static void main(String[] args) {
        SpringApplication.run(MessageApplication.class);
    }

}

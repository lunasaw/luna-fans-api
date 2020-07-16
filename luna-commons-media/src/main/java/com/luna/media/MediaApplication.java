package com.luna.media;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Package: com.luna.media
 * @ClassName: MediaApplication
 * @Author: luna
 * @CreateTime: 2020/7/16 20:44
 * @Description:
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MediaApplication {
    public static void main(String[] args) {
        SpringApplication.run(MediaApplication.class);
    }
}

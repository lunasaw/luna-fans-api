package com.luna.baidu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Package: com.luna.baidu
 * @ClassName: BaiduApplication
 * @Author: luna
 * @CreateTime: 2020/7/16 16:08
 * @Description:
 */
@EnableScheduling
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class BaiduApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaiduApplication.class, args);
    }
}

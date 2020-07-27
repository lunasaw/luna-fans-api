package com.luna.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Package: com.luna.api
 * @ClassName: ApiApplication
 * @Author: luna
 * @CreateTime: 2020/7/16 16:08
 * @Description:
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }
}

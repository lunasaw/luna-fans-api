package com.luna.ali;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Package: com.luna.ali
 * @ClassName: AliApplication
 * @Author: luna
 * @CreateTime: 2020/7/16 16:22
 * @Description:
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class AliApplication {
    public static void main(String[] args) {
        SpringApplication.run(AliApplication.class, args);
    }
}

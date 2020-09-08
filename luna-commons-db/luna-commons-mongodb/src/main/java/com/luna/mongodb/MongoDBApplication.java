package com.luna.mongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Package: com.luna.mongodb.constant
 * @ClassName: MongoDBApplication
 * @Author: luna
 * @CreateTime: 2020/9/3 15:22
 * @Description:
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MongoDBApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongoDBApplication.class);
    }
}

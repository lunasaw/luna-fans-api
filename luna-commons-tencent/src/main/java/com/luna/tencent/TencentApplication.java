package com.luna.tencent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Package: com.luna.tencent
 * @ClassName: TencentApplication
 * @Author: luna
 * @CreateTime: 2020/7/16 16:42
 * @Description:
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class TencentApplication {
    public static void main(String[] args) {
        SpringApplication.run(TencentApplication.class, args);
    }
}

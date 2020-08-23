package com.luna.db;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Package: com.luna.message
 * @ClassName: MessageApplication
 * @Author: luna
 * @CreateTime: 2020/7/16 16:54
 * @Description:
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.luna.db.**.dao"})
public class DbApplication {
    public static void main(String[] args) {
        SpringApplication.run(DbApplication.class);
    }

}

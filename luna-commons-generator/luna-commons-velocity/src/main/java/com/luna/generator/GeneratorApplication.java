package com.luna.generator;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Package: com.luna.cache
 * @ClassName: CacheApplication
 * @Author: luna
 * @CreateTime: 2020/9/13 21:30
 * @Description:
 */
@SpringBootApplication
@MapperScan("com.luna.generator.mapper")
public class GeneratorApplication {
    public static void main(String[] args) {
        SpringApplication.run(GeneratorApplication.class);
    }
}

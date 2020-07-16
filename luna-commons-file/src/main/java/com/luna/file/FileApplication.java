package com.luna.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Package: com.luna.file
 * @ClassName: FileApplicant
 * @Author: luna
 * @CreateTime: 2020/7/16 15:44
 * @Description:
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class FileApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileApplication.class, args);
    }
}

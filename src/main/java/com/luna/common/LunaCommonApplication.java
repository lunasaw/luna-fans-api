package com.luna.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class LunaCommonApplication {

    public static void main(String[] args) {
        SpringApplication.run(LunaCommonApplication.class, args);
    }

}

package io.github.lunasaw;

import com.luna.api.email.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author chenzhangyue
 * 2023/3/3
 */
@SpringBootApplication
public class FansApi {

    @Autowired
    private MessageService messageService;

    public static void main(String[] args) {
        SpringApplication.run(FansApi.class, args);
    }
}

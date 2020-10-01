package com.luna.clientone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;

/**
 * @Package: com.luna.jwt
 * @ClassName: JwtApplication
 * @Author: luna
 * @CreateTime: 2020/9/28 14:52
 * @Description:
 */
@SpringBootApplication
@EnableOAuth2Sso
public class ClientOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientOneApplication.class);
    }
}

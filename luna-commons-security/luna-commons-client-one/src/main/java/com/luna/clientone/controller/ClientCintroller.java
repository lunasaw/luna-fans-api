package com.luna.clientone.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Package: com.luna.clientone.controller
 * @ClassName: ClientCintroller
 * @Author: luna
 * @CreateTime: 2020/10/1 19:54
 * @Description:
 */
@RestController
@RequestMapping("/user")
public class ClientCintroller {

    @GetMapping("/getUser")
    public Object getUser(Authentication authentication) {
        return authentication;
    }

}

package com.luna.oauth.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Package: com.luna.oauth.controller
 * @ClassName: UserController
 * @Author: luna
 * @CreateTime: 2020/9/30 14:48
 * @Description:
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping("/getCurrentUser")
    public Object getCurrentUser(Authentication authentication) {
        return authentication.getPrincipal();
    }

}

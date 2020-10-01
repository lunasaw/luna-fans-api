package com.luna.oauth.controller;

import com.luna.jwt.util.JjwtUtil;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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
    public Object getCurrentUser(Authentication authentication, HttpServletRequest request) {

        String header = request.getHeader("Authorization");

        String bearer = header.substring(header.lastIndexOf("bearer") + 7);

        return authentication.getPrincipal();
    }

    @PostMapping("/parseOauth")
    public Object parse(Authentication authentication, HttpServletRequest request) {

        String header = request.getHeader("Authorization");

        String bearer = header.substring(header.lastIndexOf("bearer") + 7);

        return JjwtUtil.parseBaseJwt(bearer);
    }

}

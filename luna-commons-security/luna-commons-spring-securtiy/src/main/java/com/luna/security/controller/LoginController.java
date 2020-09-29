package com.luna.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luna.security.service.UserService;

/**
 * @Package: com.luna.jwt.controller
 * @ClassName: LoginController
 * @Author: luna
 * @CreateTime: 2020/9/28 16:07
 * @Description:
 */
@RequestMapping("/")
@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @Secured("ROLE_admin")
    @PostMapping("toMain")
    public String main() {
        System.out.println("转发到main");
        return "main";
    }

    @GetMapping("redirectToMain")
    public String redirectToMain() {
        System.out.println("自定义成功处理器重定向到main");
        return "main";
    }

    @GetMapping("redirectToError")
    public String redirectToError() {
        System.out.println("自定义失败处理器重定向到toError");
        return "toError";
    }

    @GetMapping("index")
    public String login() {
        System.out.println("来到登录页面");
        return "login";
    }

    @PostMapping("toError")
    public String toError() {
        System.out.println("转发到error");
        return "toError";
    }

    /**
     * PreAuthorize 允许角色ROLE_ 也可以不 但是配置类不能ROLE_ 开头 均严格大小写
     * 
     * @return
     */
    @PreAuthorize("hasRole('admin')")
    @GetMapping("toMain1")
    public String main1() {
        System.out.println("转发到main1");
        return "main1";
    }

}

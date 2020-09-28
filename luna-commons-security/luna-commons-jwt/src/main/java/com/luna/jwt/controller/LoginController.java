package com.luna.jwt.controller;

import com.luna.jwt.entity.User;
import com.luna.jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Package: com.luna.jwt.controller
 * @ClassName: LoginController
 * @Author: luna
 * @CreateTime: 2020/9/28 16:07
 * @Description:
 */
@RestController
@RequestMapping("/")
public class LoginController {

    @Autowired
    UserService userService;

    @PostMapping("login")
    public User login(String userName, String password) {
        System.out.println(userName + password);
        User user = new User();
        user.setName(userName);
        user.setPassword(password);
        return userService.getByEntity(user);
    }

}

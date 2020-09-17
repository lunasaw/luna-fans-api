package com.luna.generator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Package: com.luna.generator.controller
 * @ClassName: BaseController
 * @Author: luna
 * @CreateTime: 2020/9/17 17:49
 * @Description:
 */
@Controller
public class BaseController {

    @GetMapping("/")
    public String index() {
        return "tool/gen/gen";
    }
}

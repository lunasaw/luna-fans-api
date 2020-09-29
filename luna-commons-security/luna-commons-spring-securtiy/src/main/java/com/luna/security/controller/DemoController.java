package com.luna.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luna.common.dto.ResultDTO;

/**
 * @Package: com.luna.security.controller
 * @ClassName: RestController
 * @Aut luna
 * @CreateTime: 2020/9/29 15:16
 * @Description:
 */
@RestController
@RequestMapping("/")
public class DemoController {

    @GetMapping("demo")
    public ResultDTO<Void> getDemo() {
        return ResultDTO.success();
    }

    @PostMapping("demo")
    public ResultDTO<Void> postDemo() {
        return ResultDTO.success();
    }

}

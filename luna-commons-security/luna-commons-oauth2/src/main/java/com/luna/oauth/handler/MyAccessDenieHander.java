package com.luna.oauth.handler;

import com.alibaba.fastjson.JSON;
import com.luna.common.dto.ResultDTO;
import com.luna.common.http.HttpUtilsConstant;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Package: com.luna.security.hander
 * @ClassName: MyAccessDenieHander
 * @Author: luna
 * @CreateTime: 2020/9/29 16:25
 * @Description:
 */
@Component
public class MyAccessDenieHander implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
        AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setHeader("Content-Type", HttpUtilsConstant.JSON);
        PrintWriter writer = response.getWriter();
        writer.write(JSON.toJSONString(ResultDTO.failure()));
        writer.flush();
        writer.close();
    }
}

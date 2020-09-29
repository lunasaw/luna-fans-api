package com.luna.security.hander;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Package: com.luna.security.hander
 * @ClassName: MyAuthenticationFailureHandler
 * @Author: luna
 * @CreateTime: 2020/9/29 13:56
 * @Description:
 */
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private String url;

    public MyAuthenticationFailureHandler(String url) {
        this.url = url;
    }

    public MyAuthenticationFailureHandler() {}

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException exception) throws IOException, ServletException {
        System.out.println(request.getRemoteAddr());
        response.sendRedirect(url);
    }
}

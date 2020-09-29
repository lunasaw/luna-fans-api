package com.luna.security.hander;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Package: com.luna.security.hander
 * @ClassName: MyAuthenticationSuccessHandler
 * @Author: luna
 * @CreateTime: 2020/9/29 13:46
 * @Description:
 */
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private String url;

    public MyAuthenticationSuccessHandler(String url) {
        this.url = url;
    }

    public MyAuthenticationSuccessHandler() {}

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws IOException, ServletException {
        System.out.println(request.getRemoteAddr());
        response.sendRedirect(url);
    }
}

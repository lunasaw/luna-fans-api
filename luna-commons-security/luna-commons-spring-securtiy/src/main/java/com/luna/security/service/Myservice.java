package com.luna.security.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * @Package: com.luna.security.service
 * @ClassName: Myservice
 * @Author: luna
 * @CreateTime: 2020/9/29 16:51
 * @Description:
 */
public interface Myservice {

    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}

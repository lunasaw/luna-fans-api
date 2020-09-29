package com.luna.security.service.impl;

import com.luna.security.service.Myservice;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * @Package: com.luna.security.service.impl
 * @ClassName: MyServiceImpl
 * @Author: luna
 * @CreateTime: 2020/9/29 16:52
 * @Description:
 */
@Component
public class MyServiceImpl implements Myservice {

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object details = authentication.getDetails();
        if (details instanceof UserDetails) {
            UserDetails userDetails = (UserDetails)details;
            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
            // 判断当前访问的路径是否存在与当前认证用户权限中
            System.out.println(request.getRequestURI());
            return authorities.contains(new SimpleGrantedAuthority(request.getRequestURI()));
        }
        return false;
    }
}

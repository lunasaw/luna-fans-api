package com.luna.security.service;

import com.luna.common.dto.constant.ResultCode;
import com.luna.common.exception.base.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @Package: com.luna.security.service
 * @ClassName: UserService
 * @Author: luna
 * @CreateTime: 2020/9/28 21:26
 * @Description:
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        if ("admin".equals(username) == false) {
            throw new BaseException(ResultCode.ERROR_SYSTEM_EXCEPTION, ResultCode.MSG_ERROR_SYSTEM_EXCEPTION);
        }

        // 比较密码 注册时已经加密过
        String password = passwordEncoder.encode("admin123");

        return new User(username, password,
            AuthorityUtils
                .commaSeparatedStringToAuthorityList("demo,ROLE_user,ROLE_admin,/insert,/update,/delete,/select"));
    }
}

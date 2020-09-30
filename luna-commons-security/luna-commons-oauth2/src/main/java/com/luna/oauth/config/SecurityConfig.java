package com.luna.oauth.config;

import com.luna.oauth.handler.MyAccessDenieHander;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Package: com.luna.security.config
 * @ClassName: SecurityConfig
 * @Author: luna
 * @CreateTime: 2020/9/28 21:24
 * @Description:
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAccessDenieHander accessDenieHander;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 所有的请求都必须认证
        http.authorizeRequests()
            // 登录页不需要认证
            .antMatchers("/oauth/**", "/login/**", "/logout/**").permitAll()
            .anyRequest().authenticated()
            .and().formLogin().permitAll()
            .and().csrf().disable();

        // 异常处理
        http.exceptionHandling().accessDeniedHandler(accessDenieHander);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

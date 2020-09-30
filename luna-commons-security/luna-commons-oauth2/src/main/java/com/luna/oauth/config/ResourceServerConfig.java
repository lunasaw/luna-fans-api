package com.luna.oauth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @Package: com.luna.oauth.config
 * @ClassName: ResourceServerConfig
 * @Author: luna
 * @CreateTime: 2020/9/30 14:08
 * @Description:
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            // 所有请求均需认证
            .authorizeRequests()
            .anyRequest().authenticated()
            .and()
            // 匹配资源
            .requestMatchers()
            .antMatchers("/user/**");
    }
}

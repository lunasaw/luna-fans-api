package com.luna.security.config;

import com.luna.security.hander.MyAccessDenieHander;
import com.luna.security.hander.MyAuthenticationFailureHandler;
import com.luna.security.hander.MyAuthenticationSuccessHandler;
import com.luna.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

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
    private MyAccessDenieHander       accessDenieHander;

    @Autowired
    private DataSource                dataSource;

    @Autowired
    private UserService               userService;

    @Autowired
    private PersistentTokenRepository persistentTokenRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
            .loginPage("/index")
            // 自定义用户密码入参
            .usernameParameter("username").passwordParameter("password")
            // 表单提交的地址
            .loginProcessingUrl("/login")
            // 成功后跳转请求 必须为post请求
            // .successForwardUrl("/toMain")
            // 自定义登录成功处理器
            .successHandler(new MyAuthenticationSuccessHandler("redirectToMain"))
            // 失败后跳转请求
            // .failureForwardUrl("/toError");
            // 自定义登录失败处理器
            .failureHandler(new MyAuthenticationFailureHandler("redirectToError"));

        // 所有的请求都必须认证
        http.authorizeRequests()
            // 登录页不需要认证
            .antMatchers("/index").permitAll()
            // 失败页不需要认证
            .antMatchers("/redirectToError").permitAll()
            .antMatchers("/toError").permitAll()
            .antMatchers("/css/**", "/js/**", "/images/**").permitAll()
            // 放行images下的所有png图片
            // .antMatchers("/images/*.png").permitAll()
            // 放行images下的所有png图片 正则表达式
            // .regexMatchers(".+[.]png").permitAll()
            // .regexMatchers("/demo").permitAll()
            // 放行指定请求方式的指定路径
            // .regexMatchers(HttpMethod.POST, "/demo").permitAll()
            // .mvcMatchers("/demo").servletPath("/xxxx").permitAll()
            // 权限控制
            // .antMatchers("/toMain1").hasAnyAuthority("admin","admiN")
            // .antMatchers("/toMain1").hasAuthority("admin")
            // 角色控制
            // .antMatchers("/toMain1").hasRole("user")
            // .antMatchers("/toMain1").hasAnyRole("user", "admin")
            // 基于IP地址
            // .antMatchers(HttpMethod.POST, "/toMain1").hasIpAddress("127.0.0.1")
            // .antMatchers("/toMain1").hasIpAddress("127.0.0.1")
            // access 控制
            // .antMatchers("/toMain1").access("hasRole('user')")
            // 自定义access方法
            // .anyRequest().access("@myServiceImpl.hasPermission(request,authentication)");
            .anyRequest().authenticated();

        // 异常处理
        http.exceptionHandling().accessDeniedHandler(accessDenieHander);

        // 记住我设置数据源
        http.rememberMe()
            .tokenRepository(persistentTokenRepository)
            // .rememberMeParameter()
            // 过期时间
            .tokenValiditySeconds(60)
            // 自定义登录逻辑
            .userDetailsService(userService);

        http.logout()
            // 自定义退出请求地址
            // .logoutUrl("/logout")
            // 退出成功的跳转请求
            .logoutSuccessUrl("/index");

        // http.csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        // 自动建表
        // jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }
}

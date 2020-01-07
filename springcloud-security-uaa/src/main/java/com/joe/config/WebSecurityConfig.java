package com.joe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @program: springboot-learn
 * @description: security配置器
 * @author: xiaoqiaohui
 * @create: 2019-12-27 12:59
 **/
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)//开启方法授权拦截，进行权限校验
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    //认证管理器
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 密码编码器
     * @return
     */
    @Bean
    protected PasswordEncoder passwordEncoder(){
        //不需要对采取编码，直接明文比较
        //return NoOpPasswordEncoder.getInstance();
        //采用加密模式
        return new BCryptPasswordEncoder();
    }

    /**
     * 安全拦截机制
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //security 为防止CSRF（跨站请求 403）限制处了get以外的大部分请求
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/r/r1").hasAnyAuthority("p1")
                .antMatchers("/login*").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
        ;
    }

}

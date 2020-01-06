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
@EnableGlobalMethodSecurity(prePostEnabled = true)//开启方法授权拦截，进行权限校验
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
        http.csrf().disable()//屏蔽CSRF控制，即spring security 不再限制CSRF
                .authorizeRequests()
                /*.antMatchers("/r/r1").hasAuthority("p1")//增加两个web授权路径
                .antMatchers("/r/r2").hasAuthority("p2")*/
                .antMatchers("/r/**").authenticated()//所有/r/**的请求必须认证通过
                .anyRequest().permitAll()//除了/r/**，其它的访问请求可以访问
                .and()
                .formLogin()//允许表单登陆
                .loginPage("/login-view")//登陆页面
                .loginProcessingUrl("/login")
                .successForwardUrl("/login-success")//自定义登陆成功的页面地址
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)//会话设置，如果没有session就创建并使用
                .and()
                .logout()
                .logoutUrl("/logout")//自定义退出页面
                .logoutSuccessUrl("/login-view?logout");
    }

}

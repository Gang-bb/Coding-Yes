package com.gangbb.firsttest.config;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author : Gangbb
 * @ClassName : SecurityConfig
 * @Description :
 * @Date : 2021/3/3 8:31
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        //设置访问需要ADMIN权限
        //httpSecurity.authorizeRequests(req -> req.mvcMatchers("/api/hello").hasRole("ADMIN"));
        httpSecurity
                .formLogin(Customizer.withDefaults()) //跳转到默认登录页
                .authorizeRequests(req -> req.mvcMatchers("/api/hello").authenticated());//表明不检查权限只检查身份
    }
}

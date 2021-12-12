package com.gangbb.chapter03.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * @author : Gangbb
 * @ClassName : LoginSecurityConfig
 * @Description :
 * @Date : 2021/3/4 17:10
 */
@Configuration
@Order(100)
public class LoginSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 表单登录配置(拆分配置文件后)
     * @param httpSecurity
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .formLogin(form -> form.loginPage("/login").permitAll()
                        .failureHandler(jsonLoginFailureHandler())
                        .defaultSuccessUrl("/")
                        .successHandler(jsonLoginSuccessHandler()))
                .csrf(csrf -> withDefaults())
                .logout(logout -> logout.logoutUrl("/perform_logout"))
                .rememberMe(rememberMe -> rememberMe
                        .key("someSecret")
                        .tokenValiditySeconds(86400))
                .authorizeRequests(authorizeRequests -> authorizeRequests
                        .anyRequest().authenticated());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    /**
     * 登陆失败 返回封装(函数式编程)
     * @return
     */
    private AuthenticationFailureHandler jsonLoginFailureHandler() {
        return (req, res, exp) -> {
            res.setStatus(HttpStatus.UNAUTHORIZED.value());
            res.setContentType(MediaType.APPLICATION_JSON_VALUE);
            res.setCharacterEncoding("UTF-8");
            Map<String, Object> errData = new HashMap<>();
            errData.put("title", "认证失败");
            errData.put("details", exp.getMessage());
            ObjectMapper objectMapper = new ObjectMapper();
            res.getWriter().println(objectMapper.writeValueAsString(errData));
        };
    }

    /**
     * 登陆成功 返回封装(函数式编程)
     * @return
     */
    private AuthenticationSuccessHandler jsonLoginSuccessHandler() {
        return (req, res, auth) -> {
            ObjectMapper objectMapper = new ObjectMapper();
            res.setStatus(HttpStatus.OK.value());
            res.getWriter().println(objectMapper.writeValueAsString(auth));
        };
    }
}

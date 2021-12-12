package com.gangbb.chapter02.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gangbb.chapter02.security.filter.RestAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * @author : Gangbb
 * @ClassName : SecurityConfig
 * @Description :
 * @Date : 2021/3/3 8:31
 */
@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * WebSecurityConfigurerAdapter类中configure的另一种写法(用and)
     */
//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception{
//        httpSecurity
//                .authorizeRequests()
//                .antMatchers("/api/**").hasRole("USER")
//                .anyRequest().authenticated() //认证请求对象配置相关到此结束
//                .and()  // 用and()分隔配置下一个请求
//                .formLogin().loginPage("/login").usernameParameter("username1")  //表单配置
//                .and()  // 用and()分隔配置下一个请求
//                .httpBasic().realmName("BA");
//    }
    /**
     * 函数式配置方法--Logout和RememberMe示例 配置
     */
//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception{
//        httpSecurity
//                .authorizeRequests(req -> req.anyRequest().authenticated())
//                .formLogin(form -> form.loginPage("/login").permitAll())
//                .httpBasic(withDefaults())
//                .csrf(csrf -> withDefaults())
//                .logout(logout -> logout.logoutUrl("/perform_logout"))
//                .rememberMe(rememberMe -> rememberMe.rememberMeCookieName("someSecret").tokenValiditySeconds(86400));
//    }
    /**
     * 登陆成功失败配置
     * @param httpSecurity
     * @throws Exception
     */
//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception{
//        httpSecurity
//                .authorizeRequests(req -> req.anyRequest().authenticated())
//                .formLogin(form -> form.loginPage("/login").permitAll()
//                                       .failureHandler(jsonLoginFailureHandler())
//                                       .defaultSuccessUrl("/")
//                                       .successHandler(jsonLoginSuccessHandler()))
//                .httpBasic(withDefaults())
//                .csrf(csrf -> withDefaults())
//                .logout(logout -> logout.logoutUrl("/perform_logout"))
//                .rememberMe(rememberMe -> rememberMe.rememberMeCookieName("someSecret").tokenValiditySeconds(86400));
//    }

    /**
     * 添加使用自定义表单过滤器
     * @param httpSecurity
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .authorizeRequests(req -> req
                        .antMatchers("/authorize/**").permitAll()
                        .antMatchers("/admin/**").hasRole("ADMIN")
                        .antMatchers("/api/**").hasRole("USER")
                        .anyRequest().authenticated())
                .addFilterAt(restAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .csrf(csrf -> csrf.ignoringAntMatchers("/api/**", "/admin/**", "/authorize/**"));
    }

    /**
     * 实例化自定义表单过滤器
     * @return
     * @throws Exception
     */
    private RestAuthenticationFilter restAuthenticationFilter() throws Exception {
        RestAuthenticationFilter filter = new RestAuthenticationFilter(objectMapper);
        filter.setAuthenticationSuccessHandler(jsonLoginSuccessHandler());
        filter.setAuthenticationFailureHandler(jsonLoginFailureHandler());
        filter.setAuthenticationManager(authenticationManager());
        filter.setFilterProcessesUrl("/authorize/login");
        return filter;
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
     * 配置忽略资源的路径
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
            .ignoring()
            .antMatchers("/public/**", "/error")
            .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    /**
     * 配置登录用户名密码
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user")
                .password(passwordEncoder().encode("12345678"))
                .roles("USER", "ADMIN");
    }

    /**
     * 密码加密
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

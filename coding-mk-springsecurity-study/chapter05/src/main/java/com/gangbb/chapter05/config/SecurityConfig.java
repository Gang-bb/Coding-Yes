package com.gangbb.chapter05.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gangbb.chapter05.security.JwtFilter;
import com.gangbb.chapter05.security.UserDetailsPasswordServiceImpl;
import com.gangbb.chapter05.security.UserDetailsServiceImpl;
import com.gangbb.chapter05.security.filter.RestAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.zalando.problem.spring.web.advice.security.SecurityProblemSupport;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * @author : Gangbb
 * @ClassName : SecurityConfig
 * @Description :
 * @Date : 2021/3/4 13:51
 */
@EnableWebSecurity(debug = true)
@Import(SecurityProblemSupport.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProblemSupport securityProblemSupport;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserDetailsPasswordServiceImpl userDetailsPasswordService;

    @Autowired
    private JwtFilter jwtFilter;

    /**
     * 多密码编码器测试配置(未拆分配置文件前)
     *  此配置中既有restful的登录配置 又有传统前后端不分离的表单登录配置
     *  restful登录不需要携带 csrf token，表单登录需要
     * @param httpSecurity
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                //设置了<iframe>标签的“同源策略”，解决了上面出现的问题
                .headers().frameOptions().sameOrigin()
                .and()
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint(securityProblemSupport)
                        .accessDeniedHandler(securityProblemSupport))
                .authorizeRequests(req -> req
                        .antMatchers("/authorize/**").hasRole("USER")
                        .antMatchers("/admin/**").hasRole("ADMIN")
                        .antMatchers("/api/**").hasRole("USER")
                        .antMatchers("/h2-console/**").permitAll()
                        .anyRequest().authenticated())
                .addFilterAt(restAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                // 添加Jwt过滤器
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf(csrf -> csrf.ignoringAntMatchers("/api/**", "/admin/**", "/authorize/**", "/h2-console/**"))
                .formLogin(form -> form.loginPage("/login").permitAll()
                        .failureHandler(jsonLoginFailureHandler())
                        .defaultSuccessUrl("/")
                        .successHandler(jsonLoginSuccessHandler()))
                .httpBasic(withDefaults())
                .logout(logout -> logout.logoutUrl("/perform_logout"))
                .rememberMe(rememberMe -> rememberMe.rememberMeCookieName("someSecret").tokenValiditySeconds(86400));
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
                .antMatchers("/error/**", "/h2-console")
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    /**
     * 配置登录用户名密码(基于内存)
     * @param auth
     * @throws Exception
     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user")
//                .password("{bcrypt}$2a$10$XEBcwHqwLYXrbyqN2r9T2..dTmpv23RKi4SxAc4vyyt7ZZh30slAy")
//                .roles("USER", "ADMIN")
//                .and()
//                .withUser("old_user")
//                .password("{SHA-1}{hxAsxbcEP2hWRFvQBR5N7hFshzwwXlZxvDjnFDLE+Vo=}d99a4315af8fe73eb6b153ff81d775ecdcafa45f")
//                .roles("USER");
//    }

    /**
     * 配置登录用户名密码(基于数据库)
     * @param auth
     * @throws Exception
     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication()
//                .withDefaultSchema()
//                .dataSource(dataSource)
//                .passwordEncoder(mulPasswordEncoder())
//                .withUser("user")
//                .password("{bcrypt}$2a$10$XEBcwHqwLYXrbyqN2r9T2..dTmpv23RKi4SxAc4vyyt7ZZh30slAy")
//                .roles("USER", "ADMIN")
//                .and()
//                .withUser("old_user")
//                .password("{SHA-1}{hxAsxbcEP2hWRFvQBR5N7hFshzwwXlZxvDjnFDLE+Vo=}d99a4315af8fe73eb6b153ff81d775ecdcafa45f")
//                .roles("USER");
//    }

    /**
     * 配置登录用户名密码(基于H2数据库 读取.sql 文件)
     * @param auth
     * @throws Exception
     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .passwordEncoder(mulPasswordEncoder());
//    }


    /**
     * (基于H2数据库 读取.sql 文件) 并注入自定义UserDetailsService 和 配置密码自动升级服务
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .userDetailsPasswordManager(userDetailsPasswordService)
                .passwordEncoder(mulPasswordEncoder());
    }

    /**
     * DelegatingPasswordEncoder 多编码器共存校验密码
     * @return
     */
    @Bean
    public DelegatingPasswordEncoder mulPasswordEncoder() {
        // 默认编码算法的 Id
        String idForEncode = "bcrypt";

        // 要支持的多种编码器
        Map<String, PasswordEncoder> encoders = new HashMap<String, PasswordEncoder>();
        encoders.put(idForEncode, new BCryptPasswordEncoder());
        encoders.put("SHA-1", new MessageDigestPasswordEncoder("SHA-1"));

        return new DelegatingPasswordEncoder(idForEncode, encoders);
    }

    /**
     * 密码编码器加密
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

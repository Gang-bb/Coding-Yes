package com.gangbb.chapter02.security.filter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author : Gangbb
 * @ClassName : RestAuthenticationFilter
 * @Description : 自定义表单登录逻辑的过滤器
 * @Date : 2021/3/4 11:46
 */
public class RestAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private ObjectMapper objectMapper;

    public RestAuthenticationFilter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        UsernamePasswordAuthenticationToken authRequest;
        try (InputStream is = request.getInputStream()) {
            JsonNode jsonNode = objectMapper.readTree(is);

            String username = jsonNode.get("username").textValue();
            String password = jsonNode.get("password").textValue();
            authRequest = new UsernamePasswordAuthenticationToken(username, password);
        } catch (IOException e) {
            throw new BadCredentialsException("没有找到用户名或密码参数");
        }
        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }
}
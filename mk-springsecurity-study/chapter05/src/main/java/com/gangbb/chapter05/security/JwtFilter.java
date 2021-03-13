package com.gangbb.chapter05.security;

import com.gangbb.chapter05.config.AppProperties;
import com.gangbb.chapter05.util.CollectionUtil;
import com.gangbb.chapter05.util.JwtUtil;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * @author : Gangbb
 * @ClassName : JwtFilter
 * @Description :
 * @Date : 2021/3/5 14:43
 */
@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private AppProperties appProperties;


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if(checkJwtToken(httpServletRequest)) {
            validateToken(httpServletRequest)
                    .filter(claims -> claims.get("authorities") != null) //如果值存在
                    .ifPresent(this::setupSpringAuthentication);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    /**
     * 根据用户信息 构建 UsernamePasswordAuthenticationToken对象 并传入 SecurityContextHolder
     * @param claims
     */
    private void setupSpringAuthentication(Claims claims) {
        val rawList = CollectionUtil.convertObjectToList(claims.get("authorities"));
        val authorities = rawList.stream()
                .map(String::valueOf)
                .map(SimpleGrantedAuthority::new)
                .collect(toList());
        val authentication = new UsernamePasswordAuthenticationToken(claims.getSubject(), null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }


    /**
     * 解析校验token
     * @param req
     * @return
     */
    private Optional<Claims> validateToken(HttpServletRequest req) {
        // 获取token(做了替换前缀Prefix的操作)
        String jwtToken = req.getHeader(appProperties.getJwt().getHeader()).replace(appProperties.getJwt().getPrefix(), "");
        try {
            //解析token Optional.of() 返回一个指定非null值的Optional
            return Optional.of(Jwts.parserBuilder().setSigningKey(JwtUtil.KEY).build().parseClaimsJws(jwtToken).getBody());
        } catch (ExpiredJwtException | SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    /**
     * 检查 JWT Token 是否在 HTTP 报头中
     *
     * @param req HTTP 请求
     * @return 是否有 JWT Token
     */
    private boolean checkJwtToken(HttpServletRequest req) {
        String authenticationHeader = req.getHeader(appProperties.getJwt().getHeader());
        return authenticationHeader != null && authenticationHeader.startsWith(appProperties.getJwt().getPrefix());
    }
}

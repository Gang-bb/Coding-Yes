package com.gangbb.chapter05.service;

import com.gangbb.chapter05.domain.Auth;
import com.gangbb.chapter05.repository.RoleRepo;
import com.gangbb.chapter05.repository.UserRepo;
import com.gangbb.chapter05.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author : Gangbb
 * @ClassName : UserService
 * @Description :
 * @Date : 2021/3/5 17:09
 */
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return JWT
     */
    public Auth login(String username, String password) {
        return userRepo.findOptionalByUsername(username)
                .filter(user -> passwordEncoder.matches(password, user.getPassword()))
                .map(user -> new Auth(jwtUtil.createAccessToken(user), jwtUtil.createRefreshToken(user)))
                .orElseThrow(() -> new AccessDeniedException("用户名密码错误"));
    }
}

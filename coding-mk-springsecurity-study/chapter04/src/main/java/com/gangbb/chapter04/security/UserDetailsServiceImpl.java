package com.gangbb.chapter04.security;

import com.gangbb.chapter04.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author : Gangbb
 * @ClassName : UserDetailsServiceImpl
 * @Description :
 * @Date : 2021/3/5 8:43
 */
@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findOptionalByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("未找到用户名"));
    }
}

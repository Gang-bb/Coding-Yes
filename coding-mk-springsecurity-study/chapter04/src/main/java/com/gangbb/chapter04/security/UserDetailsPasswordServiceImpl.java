package com.gangbb.chapter04.security;

import com.gangbb.chapter04.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.stereotype.Service;

/**
 * @author : Gangbb
 * @ClassName : UserDetailsPasswordServiceImpl
 * @Description :
 * @Date : 2021/3/5 9:12
 */
@RequiredArgsConstructor
@Service
public class UserDetailsPasswordServiceImpl implements UserDetailsPasswordService {

    private final UserRepo userRepo;

    @Override
    public UserDetails updatePassword(UserDetails userDetails, String newPassword) {
        return userRepo.findOptionalByUsername(userDetails.getUsername())
                .map(userFromDb -> (UserDetails) userRepo.save(userFromDb.withPassword(newPassword)))
                .orElse(userDetails);
    }
}

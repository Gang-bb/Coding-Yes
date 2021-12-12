package com.gangbb.chapter05.util;

import com.gangbb.chapter05.domain.Role;
import com.gangbb.chapter05.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;
import java.util.Set;

import static com.gangbb.chapter05.util.Constants.ROLE_ADMIN;
import static com.gangbb.chapter05.util.Constants.ROLE_USER;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : Gangbb
 * @ClassName : JwtUtilTest
 * @Description :
 * @Date : 2021/3/5 11:59
 */
@ExtendWith(SpringExtension.class)
public class JwtUtilTest {

    private JwtUtil jwtUtil;

    @BeforeEach
    public void setup() {
        jwtUtil = new JwtUtil();
    }

    @Test
    public void givenUserDetails_thenCreateTokenSuccess() {
        String username = "user";
        Set authorities = new HashSet();
        authorities.add(new Role(ROLE_USER));
        authorities.add(new Role(ROLE_ADMIN));

        User user = new User(authorities);

    }


}
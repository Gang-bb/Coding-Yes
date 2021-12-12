package com.gangbb.chapter05.rest;

import com.gangbb.chapter05.domain.Auth;
import com.gangbb.chapter05.domain.dto.LoginDto;
import com.gangbb.chapter05.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;

/**
 * @author : Gangbb
 * @ClassName : AuthorizeResource
 * @Description :
 * @Date : 2021/3/4 15:14
 */
@RestController
@RequestMapping("/authorize")
public class AuthorizeResource {
    @Autowired
    private UserService userService;

    @GetMapping(value="greeting")
    public String sayHello() {
        return "hello";
    }

    @PostMapping("/token")
    public Auth login(@Valid @RequestBody LoginDto loginDTO) {
        return userService.login(loginDTO.getUsername(), loginDTO.getPassword());
    }

}

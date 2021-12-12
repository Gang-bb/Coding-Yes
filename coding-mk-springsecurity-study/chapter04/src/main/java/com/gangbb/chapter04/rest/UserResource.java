package com.gangbb.chapter04.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author : Gangbb
 * @ClassName : UserResource
 * @Description :
 * @Date : 2021/3/4 7:52
 */
@RestController
@RequestMapping("/api")
public class UserResource {
    @GetMapping("/hello")
    public String hello(){
        return "hello spring security";
    }

    @GetMapping("/principal")
    public Principal getCurrentPrincipalName(Principal principal) {
        return principal;
    }
}

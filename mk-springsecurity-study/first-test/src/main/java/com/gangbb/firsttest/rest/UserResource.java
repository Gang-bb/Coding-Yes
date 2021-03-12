package com.gangbb.firsttest.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Gangbb
 * @ClassName : UserResource
 * @Description :
 * @Date : 2021/3/3 8:23
 */
@RestController
@RequestMapping("/api")
public class UserResource {
    @GetMapping("/hello")
    public String hello(){
        return "hello spring security";
    }
}

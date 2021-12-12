package com.gangbb.chapter02.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

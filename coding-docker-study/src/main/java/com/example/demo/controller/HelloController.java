package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Gangbb
 * @Description TODO
 * @Date 2021/7/15
 **/
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello docker";
    }
}
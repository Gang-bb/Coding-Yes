package com.gangbb.chapter03.rest;

import com.gangbb.chapter03.domain.dto.UserDto;
import org.springframework.security.access.AccessDeniedException;

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
    @GetMapping(value="greeting")
    public String sayHello(@RequestBody UserDto userDto) {
        return userDto.getEmail();
    }

    @PostMapping(value="register")
    public UserDto register(@Valid @RequestBody UserDto userDto) {
        return userDto;
    }


    @GetMapping("/problem")
    public void raiseProblem() {
        throw new AccessDeniedException("You do not have the privilege");
    }

}

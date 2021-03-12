package com.gangbb.chapter03.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author : Gangbb
 * @ClassName : User
 * @Description :
 * @Date : 2021/3/4 15:08
 */
@Data
public class User {
    private String username;

    private String password;

    private String email;

    private String name;
}

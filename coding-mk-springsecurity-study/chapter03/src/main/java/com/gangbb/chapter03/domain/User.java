package com.gangbb.chapter03.domain;

import lombok.Data;

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

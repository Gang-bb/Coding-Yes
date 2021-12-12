package com.gangbb.chapter05.domain.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author : Gangbb
 * @ClassName : UserDto
 * @Description :
 * @Date : 2021/3/4 15:10
 */

@Data
public class UserDto implements Serializable {

    @NotNull
    @NotBlank
    @Size(min = 4, max = 50, message = "用户名长度必须在4到50个字符之间")
    private String username;

    @NotNull
    @NotBlank
    @Size(min = 8, max = 20, message = "用户名长度必须在8到20个字符之间")
    private String password;

    @NotNull
    @NotBlank
    @Size(min = 8, max = 20, message = "用户名长度必须在8到20个字符之间")
    private String matchingPassword;

    @NotNull
    @Email
    private String email;

    @NotNull
    @NotBlank
    @Size(min = 4, max = 50, message = "姓名长度必须在4到50个字符之间")
    private String name;
}

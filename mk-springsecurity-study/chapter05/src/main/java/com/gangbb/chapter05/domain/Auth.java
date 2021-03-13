package com.gangbb.chapter05.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author : Gangbb
 * @ClassName : Auth
 * @Description :
 * @Date : 2021/3/5 17:07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Auth implements Serializable {
    private String accessToken;
    private String refreshToken;
}


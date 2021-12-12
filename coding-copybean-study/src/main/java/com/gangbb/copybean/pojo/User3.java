package com.gangbb.copybean.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author Gangbb
 * @Description TODO
 * @Date 2021/8/9
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User3 implements Serializable {

    private static final long serialVersionUID = -3333107267346753671L;
    private Integer id;
    private String loginName;
    private String password;

}
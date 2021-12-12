package com.gangbb.dozerstudy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author Gangbb
 * @Description User实体类
 * @Date 2021/8/7
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = -5308107267346753671L;
    private Integer id;
    private String loginName;
    private String password;
    private Date birthday;
    private OtherInfo otherInfo;
}
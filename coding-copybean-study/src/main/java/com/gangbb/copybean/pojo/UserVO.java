package com.gangbb.copybean.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Author Gangbb
 * @Description TODO
 * @Date 2021/8/9
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {
    private Integer id;
    // 与user2对应的 loginName 字段名不同
    private String name;
    private String password;
    // 与User2的 birthday 类型不同
    private String birthday;
    // 对应User2中的typeEnum属性
    private Integer type;
    // 对应User2 的 rate
    private String rate;
    // 对应User2 的 rate2
    private BigDecimal rate2;
    // 对应User2 的 rate3
    private Double rate3;
}
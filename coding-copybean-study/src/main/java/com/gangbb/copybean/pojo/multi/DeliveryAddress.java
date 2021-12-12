package com.gangbb.copybean.pojo.multi;

import lombok.Data;

/**
 * @Author Gangbb
 * @Description TODO
 * @Date 2021/8/9
 **/
@Data
public class DeliveryAddress {
    // 对应Person属性 firstName
    private String firstName;
    // 对应Person属性 lastName
    private String lastName;
    // 对应Person属性 height
    private Integer height;
    // 对应Address属性 street
    private String street;
    // 对应Address属性 zipCode
    private Integer zipCode;
    // 对应Address属性 houseNo
    private Integer houseNumber;
    // (Person、Address都有description)，这里对应Person属性 description
    private String description;
}
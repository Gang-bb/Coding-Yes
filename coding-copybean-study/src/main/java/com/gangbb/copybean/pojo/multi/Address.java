package com.gangbb.copybean.pojo.multi;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author Gangbb
 * @Description TODO
 * @Date 2021/8/9
 **/
@Data
@AllArgsConstructor
public class Address {
    private String street;
    private int zipCode;
    private int houseNo;
    private String description;
}
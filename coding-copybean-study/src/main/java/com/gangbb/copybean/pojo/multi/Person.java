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
public class Person {
    private String firstName;
    private String lastName;
    private int height;
    private String description;
}
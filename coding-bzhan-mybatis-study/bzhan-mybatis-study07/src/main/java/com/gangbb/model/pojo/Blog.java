package com.gangbb.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author : Gangbb
 * @ClassName : Blog
 * @Description :
 * @Date : 2021/2/9 13:17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Blog {
    private Integer id;
    private String title;
    private String author;
    private Date createTime;
    private Integer views;
}

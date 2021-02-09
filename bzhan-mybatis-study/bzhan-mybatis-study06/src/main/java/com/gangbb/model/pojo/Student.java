package com.gangbb.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Gangbb
 * @ClassName : Student
 * @Description :
 * @Date : 2021/2/9 8:57
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private Integer id;
    private String name;
    private Integer tid;

    //private Teacher teacher;
}

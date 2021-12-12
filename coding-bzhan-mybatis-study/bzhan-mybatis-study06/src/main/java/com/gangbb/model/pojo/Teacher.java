package com.gangbb.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author : Gangbb
 * @ClassName : Teacher
 * @Description :
 * @Date : 2021/2/9 8:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    private Integer id;
    private String name;

    private List<Student> students;
}

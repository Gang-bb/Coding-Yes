package com.gangbb.model.dao;

import com.gangbb.model.pojo.Student;

import java.util.List;

/**
 * @author : Gangbb
 * @ClassName : StudentMapper
 * @Description :
 * @Date : 2021/2/9 9:00
 */
public interface StudentMapper {
    /**
     * 查询所有学生信息，及其对应的教师信息
     */
    List<Student> getStudent();

    List<Student> getStudent2();

}

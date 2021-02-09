package com.gangbb.model.dao;

import com.gangbb.model.pojo.Student;
import com.gangbb.model.pojo.Teacher;

import java.util.List;

/**
 * @author : Gangbb
 * @ClassName : TeacherMapper
 * @Description :
 * @Date : 2021/2/9 9:00
 */
public interface TeacherMapper {
//    List<Student> getTeacher();

    Teacher getTeacher(Integer id);

    Teacher getTeacher2(Integer id);
}

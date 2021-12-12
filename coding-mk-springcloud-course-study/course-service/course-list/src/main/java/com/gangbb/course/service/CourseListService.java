package com.gangbb.course.service;

import com.gangbb.course.entity.Course;

import java.util.List;

/**
 * @author Gangbb
 * @date 2021/4/2 18:22
 * @Description: 课程列表服务
 */
public interface CourseListService {

    List<Course> getCourseList();
}
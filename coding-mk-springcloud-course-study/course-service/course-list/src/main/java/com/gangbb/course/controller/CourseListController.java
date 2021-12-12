package com.gangbb.course.controller;

import com.gangbb.course.entity.Course;
import com.gangbb.course.service.CourseListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Gangbb
 * @date 2021/4/2 18:07
 * @Description: 课程列表Controller
 */
@RestController
public class CourseListController {

    @Autowired
    CourseListService courseListService;

    @GetMapping("/courses")
    public List<Course> courseList() {
        return courseListService.getCourseList();
    }
}

package com.gangbb.course.controller;

import com.gangbb.course.client.CourseListClient;
import com.gangbb.course.entity.Course;
import com.gangbb.course.entity.CourseAndPrice;
import com.gangbb.course.entity.CoursePrice;
import com.gangbb.course.service.CoursePriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Gangbb
 * @date 2021/4/2 19:28
 * @Description: 课程价格控制器
 */
@RestController
public class CoursePriceController {

    @Autowired
    CoursePriceService coursePriceService;

    @Autowired
    CourseListClient courseListClient;


    @GetMapping("/price")
    public Integer getCoursePrice(Integer courseId) {
        CoursePrice coursePrice = coursePriceService.getCoursePrice(courseId);
        return coursePrice.getPrice();
    }

    @GetMapping("/coursesInPrice")
    public List<Course> getCourseListInPrice(Integer courseId) {
        List<Course> courses = courseListClient.courseList();
        return courses;
    }

    @GetMapping("coursesAndPrice")
    public List<CourseAndPrice> getCoursesAndPrice() {
        List<CourseAndPrice> coursesAndPrice = coursePriceService.getCoursesAndPrice();
        return coursesAndPrice;
    }
}

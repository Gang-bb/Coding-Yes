package com.gangbb.course.client;

import com.gangbb.course.entity.Course;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gangbb
 * @date 2021/4/5 13:58
 * @Description: 断路器实现类
 */
@Component
public class CourseListClientHystrix implements CourseListClient {

    @Override
    public List<Course> courseList() {
        List<Course> defaultCourses = new ArrayList<>();
        Course course = new Course();
        course.setId(1);
        course.setCourseId(1);
        course.setCourseName("默认课程");
        course.setValid(1);
        defaultCourses.add(course);
        return defaultCourses;
    }
}

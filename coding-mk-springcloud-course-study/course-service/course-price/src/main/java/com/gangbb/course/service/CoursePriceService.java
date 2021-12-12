package com.gangbb.course.service;

import com.gangbb.course.entity.CourseAndPrice;
import com.gangbb.course.entity.CoursePrice;

import java.util.List;

/**
 * @author Gangbb
 * @date 2021/4/2 19:31
 * @Description:
 */
public interface CoursePriceService {
    CoursePrice getCoursePrice(Integer courseId);

    List<CourseAndPrice> getCoursesAndPrice();
}

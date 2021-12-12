package com.gangbb.course.service.impl;

import com.gangbb.course.dao.CourseMapper;
import com.gangbb.course.entity.Course;
import com.gangbb.course.service.CourseListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Gangbb
 * @date 2021/4/2 18:23
 * @Description: 课程服务实现类
 */
@Service
public class CourseListServiceImpl implements CourseListService {

    @Autowired
    CourseMapper courseMapper;

    @Override
    public List<Course> getCourseList() {
        return courseMapper.findValidCourses();
    }
}


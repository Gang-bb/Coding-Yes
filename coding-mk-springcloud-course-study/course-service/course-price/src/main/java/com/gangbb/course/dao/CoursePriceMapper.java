package com.gangbb.course.dao;

import com.gangbb.course.entity.CoursePrice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author Gangbb
 * @date 2021/4/2 19:29
 * @Description: 课程价格Mapper类
 */
@Mapper
@Repository
public interface CoursePriceMapper {

    @Select("SELECT * FROM course_price WHERE course_id = #{courseId}")
    CoursePrice findCoursePrice(Integer courseId);
}
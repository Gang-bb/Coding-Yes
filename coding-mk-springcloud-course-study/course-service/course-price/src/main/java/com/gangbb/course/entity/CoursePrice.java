package com.gangbb.course.entity;

import java.io.Serializable;

/**
 * @author Gangbb
 * @date 2021/4/2 19:30
 * @Description: CoursePrice的实体类
 */
public class CoursePrice implements Serializable {

    Integer id;
    Integer courseId;
    Integer price;

    @Override
    public String toString() {
        return "CoursePrice{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", price=" + price +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}

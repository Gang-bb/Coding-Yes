package com.gangbb.desigin.creational.builder;

/**
 * @author Gangbb
 * @date 2021/4/16 21:11
 * @Description: 课程抽象建造者
 */
public abstract class CourseBuilder {

    public abstract void buildCourseName(String courseName);
    public abstract void buildCoursePPT(String coursePPT);
    public abstract void buildCourseVideo(String courseVideo);
    public abstract void buildCourseArticle(String courseArticle);
    public abstract void buildCourseQA(String courseQA);

    public abstract Course makeCourse();

}
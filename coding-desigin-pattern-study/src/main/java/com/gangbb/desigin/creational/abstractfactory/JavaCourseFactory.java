package com.gangbb.desigin.creational.abstractfactory;

/**
 * @author Gangbb
 * @date 2021/3/29 13:51
 * @Description: Java课程工厂类
 */
public class JavaCourseFactory implements CourseFactory{
    /**
     * 获取Java教学视频
     *
     * @return
     */
    public Video getVideo() {
        return new JavaVideo();
    }

    /**
     * 获取Java教学手记
     *
     * @return
     */
    public Article getArticle() {
        return new JavaArticle();
    }
}

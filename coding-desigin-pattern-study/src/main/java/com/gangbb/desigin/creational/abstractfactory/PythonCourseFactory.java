package com.gangbb.desigin.creational.abstractfactory;

/**
 * @author Gangbb
 * @date 2021/3/29 14:01
 * @Description: python课程工厂
 */
public class PythonCourseFactory implements CourseFactory {
    /**
     * 获取Python教学视频
     *
     * @return
     */
    public Video getVideo() {
        return new PythonVideo();
    }

    /**
     * 获取Python教学手记
     *
     * @return
     */
    public Article getArticle() {
        return new PythonArticle();
    }
}

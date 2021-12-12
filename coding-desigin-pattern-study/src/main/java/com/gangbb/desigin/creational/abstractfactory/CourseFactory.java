package com.gangbb.desigin.creational.abstractfactory;

/**
 * @author Gangbb
 * @date 2021/3/29 13:29
 * @Description: 课程工厂接口
 */
public interface CourseFactory {
    /**
     * 获取教学视频
     * @return
     */
    Video getVideo();

    /**
     * 获取教学手记
     * @return
     */
    Article getArticle();
}

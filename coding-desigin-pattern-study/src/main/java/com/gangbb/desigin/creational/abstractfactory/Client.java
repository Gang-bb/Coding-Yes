package com.gangbb.desigin.creational.abstractfactory;

/**
 * @author Gangbb
 * @date 2021/3/29 14:10
 * @Description:
 */
public class Client {
    public static void main(String[] args) {
        CourseFactory courseFactory = new JavaCourseFactory();
        Video video = courseFactory.getVideo();
        Article article = courseFactory.getArticle();
        video.produce();
        article.produce();
    }

}

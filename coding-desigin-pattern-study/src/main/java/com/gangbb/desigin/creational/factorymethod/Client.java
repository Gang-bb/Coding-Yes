package com.gangbb.desigin.creational.factorymethod;

/**
 * @author Gangbb
 * @date 2021/3/27 20:47
 * @Description: 模拟客户端类
 */
public class Client {
    public static void main(String[] args) {
        // 父类声明的引用指向子类的实现。 videoFactory为Java视频工厂
        VideoFactory videoFactory = new JavaVideoFactory();
        // 获得video
        Video video = videoFactory.getVideo();
        //生产video
        video.produce();
    }
}

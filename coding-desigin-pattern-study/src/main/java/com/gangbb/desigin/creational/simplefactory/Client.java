package com.gangbb.desigin.creational.simplefactory;

/**
 * @author Gangbb
 * @date 2021/3/27 20:47
 * @Description: 模拟客户端类
 */
public class Client {
    public static void main(String[] args) {
        //传统调用-----创建Java视频
        //Video video = new JavaVideo();
        //video.produce();

        //使用 视频简单工厂--创建视频(常规实现)
//        VideoFactory videoFactory = new VideoFactory();
//        Video video = videoFactory.getVideo("java");
//        video.produce();

        //使用 视频简单工厂--创建视频(反射实现)
        VideoFactory videoFactory = new VideoFactory();
        Video video = videoFactory.getVideo(PythonVideo.class);
        video.produce();
    }
}

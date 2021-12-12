package com.gangbb.desigin.creational.factorymethod;

/**
 * @author Gangbb
 * @date 2021/3/28 11:18
 * @Description: Java视频工厂类
 */
public class JavaVideoFactory extends VideoFactory{
    public Video getVideo() {
        return new JavaVideo();
    }
}

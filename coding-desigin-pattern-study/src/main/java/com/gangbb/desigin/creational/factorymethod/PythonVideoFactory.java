package com.gangbb.desigin.creational.factorymethod;

/**
 * @author Gangbb
 * @date 2021/3/28 11:27
 * @Description: Python视频工厂
 */
public class PythonVideoFactory extends VideoFactory {
    public Video getVideo() {
        return new PythonVideo();
    }
}

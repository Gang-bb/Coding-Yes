package com.gangbb.desigin.creational.singleton;

/**
 * @author Gangbb
 * @date 2021/4/19 11:49
 * @Description: 单例模式懒汉式
 */
public class LazySingleton {
    private static LazySingleton lazySingleton = null;

    private LazySingleton(){

    }

    public static LazySingleton getInstance(){
        if (lazySingleton == null){
            lazySingleton = new LazySingleton();
        }
        return lazySingleton;
    }
}

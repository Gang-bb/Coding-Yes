package com.gangbb.desigin.creational.singleton;

/**
 * @author Gangbb
 * @date 2021/4/19 11:54
 * @Description:
 */
public class T implements Runnable{
    public void run() {
        LazySingleton lazySingleton = LazySingleton.getInstance();
        System.out.println(Thread.currentThread().getName()+ "" +lazySingleton);
    }
}

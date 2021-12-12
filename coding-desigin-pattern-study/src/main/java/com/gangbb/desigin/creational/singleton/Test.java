package com.gangbb.desigin.creational.singleton;

/**
 * @author Gangbb
 * @date 2021/4/19 11:51
 * @Description:
 */
public class Test {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new T());
        Thread thread2 = new Thread(new T());
        thread1.run();
        thread2.run();
    }


}

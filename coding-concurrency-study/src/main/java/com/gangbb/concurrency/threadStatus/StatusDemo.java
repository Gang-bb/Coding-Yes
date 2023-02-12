package com.gangbb.concurrency.threadStatus;

import java.util.concurrent.TimeUnit;

/**
 * 演示各种状态
 *
 * @author lyx
 * @date 2023/2/12
 **/
public class StatusDemo {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println("我是线程A");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程A执行结束");
        }, "A");
        System.out.println("new实例化后的状态：" + thread.getState());
        thread.start();
        System.out.println("调用后start()的状态：" + thread.getState());
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
        System.out.println("调用后interrupt()的状态：" + thread.getState());
        System.out.println("执行结束后的状态：" + thread.getState());
    }
}

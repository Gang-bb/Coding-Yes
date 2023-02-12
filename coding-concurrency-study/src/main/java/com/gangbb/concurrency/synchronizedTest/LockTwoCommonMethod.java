package com.gangbb.concurrency.synchronizedTest;

import java.util.concurrent.TimeUnit;

/**
 * 锁一个类内两个普通方法。
 * 目的：看锁普通方法是锁是当前实例对象
 *
 * @author lyx
 * @date 2023/2/12
 **/
public class LockTwoCommonMethod {
    public static void main(String[] args) {
        Phone phone = new Phone();
        Phone phone2 = new Phone();

        /*同一对象同一把锁*/
//        new Thread(() -> {
//            phone.sendSms();
//        }, "A").start();
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        new Thread(() -> {
//            phone.call();
//        }, "B").start();


        /*不同对象，不同一把锁*/
        new Thread(() -> {
            phone.sendSms();
        }, "A").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            phone2.call();
        }, "B").start();

    }
}

class Phone {

    /**
     * synchronized锁的对象是方法的调用者
     * 两个方法用的是同一个锁,谁先拿到谁先执行
     */


    public synchronized void sendSms() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sendSms");
    }

    public synchronized void call() {
        System.out.println("call");
    }
}

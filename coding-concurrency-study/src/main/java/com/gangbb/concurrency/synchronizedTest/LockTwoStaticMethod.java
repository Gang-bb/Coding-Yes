package com.gangbb.concurrency.synchronizedTest;

import java.util.concurrent.TimeUnit;

/**
 * 锁一个类内两个静态方法。
 *
 * @author lyx
 * @date 2023/2/12
 **/
public class LockTwoStaticMethod {

    public static void main(String[] args) {
        //两个对象的class模板只有一个，static，锁的是class
        Phone2 phone2 = new Phone2();
        new Thread(()->{
            Phone2.sendSms();
        },"A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            phone2.hello();
        },"B").start();

        new Thread(()->{
            Phone2.call();
        },"B").start();
    }
}



class Phone2 {

    /**
     * 修饰静态方法: 也就是给当前类加锁，会作用于类的所有对象实例 ，进入同步代码前要获得当前class的锁。
     */
    //static 静态方法 类一加载就有了！class模板,锁的是class对象Class<Phone3> phone3Class = Phone3.class;
    //两个方法用的是同一个锁
    public synchronized static void sendSms() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sendSms");
    }

    public synchronized static void call() {
        System.out.println("call");
    }

    public synchronized void hello() {
        System.out.println("hello");
    }
}

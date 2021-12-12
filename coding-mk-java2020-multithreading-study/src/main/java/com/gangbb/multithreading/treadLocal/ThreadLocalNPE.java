package com.gangbb.multithreading.treadLocal;

/**
 * @author Gangbb
 * @date 2021/4/1 17:52
 * @Description:  模拟ThreadLocal空指针异常
 */
public class ThreadLocalNPE {
    // 定义一个Long类型的ThreadLocal对象
    ThreadLocal<Long> longThreadLocal = new ThreadLocal<Long>();

    /**
     * 给 ThreadLocal 成员 赋值
     */
    public void set(){
        longThreadLocal.set(Thread.currentThread().getId());
    }

    /**
     * 获取 ThreadLocal 成员 赋值
     * @return
     */
    public long get(){
        return longThreadLocal.get();
    }

    public static void main(String[] args) {
        ThreadLocalNPE threadLocalNPE = new ThreadLocalNPE();


        // 主线程 未调用set()赋值，直接get()获取打印
        System.out.println(threadLocalNPE.get());

        // 用子线程获取打印ThreadLocal中保存的值
//        Thread thread1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                threadLocalNPE.set();
//                System.out.println(threadLocalNPE.get());
//            }
//        });
//        thread1.start();
    }

}

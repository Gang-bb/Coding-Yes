package com.gangbb.multithreading.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Gangbb
 * @date 2021/3/30 15:07
 * @Description: 测试使用newFixedThreadPool
 */
public class TestNewFixedThreadPool {
    public static void main(String[] args) {
        // 用Executors辅助工具类 创建 newFixedThreadPool。
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(4);
        // 做1000个任务
        for (int i = 0; i < 1000; i++) {
            newFixedThreadPool.execute(new Task());
        }
    }
}

/**
 * 线程的任务
 */
class Task implements Runnable{


    public void run() {
        try {
            // 休眠500毫秒
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 打印当前线程的名字
        System.out.println(Thread.currentThread().getName());
    }
}
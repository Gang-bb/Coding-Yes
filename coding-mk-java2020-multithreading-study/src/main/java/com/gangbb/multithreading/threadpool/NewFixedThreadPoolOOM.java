package com.gangbb.multithreading.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Gangbb
 * @date 2021/3/30 15:31
 * @Description: 演示NewFixedThreadPool发生OOM错误
 */
public class NewFixedThreadPoolOOM {
    private static ExecutorService executorService = Executors.newFixedThreadPool(1);
    // 模拟执行超多错误
    public static void main(String[] args) {
        // 设置执行int的上限次数
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            executorService.execute(new SubThread());
        }
    }
}

class SubThread implements Runnable{

    public void run() {
        try {
            // 设置任务长时间休眠，达到任务不能执行完毕导致队列塞满
            Thread.sleep(1000000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

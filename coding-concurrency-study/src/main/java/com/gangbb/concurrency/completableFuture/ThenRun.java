package com.gangbb.concurrency.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * 测试CompletableFuture的ThenRun
 *
 * @author lyx
 * @date 2023/2/15
 **/
public class ThenRun {
    public static void main(String[] args) {
        ThreadFactory threadFactory = r -> new Thread(r, "MyPool-Thread-" + r.hashCode());
        ExecutorService myPool = Executors.newFixedThreadPool(10, threadFactory);


        CompletableFuture.supplyAsync(() ->{
            System.out.println("当前线程：" + Thread.currentThread().getName());
            System.out.println(Thread.currentThread().getName() + "运行完成");
            return 666;
        }, myPool).thenRun(() -> {
            System.out.println("thenRun线程：" + Thread.currentThread().getName());
            System.out.println("thenRun任务启动");
        });


        CompletableFuture.supplyAsync(() ->{
            System.out.println("当前线程：" + Thread.currentThread().getName());
            System.out.println(Thread.currentThread().getName() + "运行完成");
            return 666;
        }, myPool).thenRunAsync(() -> {
            System.out.println("thenRun线程：" + Thread.currentThread().getName());
            System.out.println("thenRun任务启动");
        }, myPool);

    }
}

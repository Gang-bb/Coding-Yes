package com.gangbb.concurrency.completableFuture.and;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * 测试AND组合关系 ThenAcceptBoth
 *
 * @author lyx
 * @date 2023-02-18
 **/
public class ThenAcceptBoth {
    public static void main(String[] args) {
        ThreadFactory threadFactory = r -> new Thread(r, "MyPool-Thread-" + r.hashCode());
        ExecutorService executor = Executors.newFixedThreadPool(10, threadFactory);

        CompletableFuture<Object> future01 =CompletableFuture.supplyAsync(() -> {
            System.out.println("任务1线程" + Thread.currentThread().getId());
            int i = 10 / 4;
            System.out.println("任务1结束：");
            return i;
        }, executor);
        CompletableFuture<Object> future02 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务2线程" + Thread.currentThread().getId());
            try {
                Thread.sleep(3000);
                System.out.println("任务2结束：");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        }, executor);


        CompletableFuture<Void> future = future01.thenAcceptBothAsync(future02, (f1, f2) -> {
            System.out.println("任务3开始...得到之前的结果：f1:" + f1 + ", f2:" + f2);
        }, executor);
    }
}

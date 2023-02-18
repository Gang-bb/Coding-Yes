package com.gangbb.concurrency.completableFuture.or;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * 测试OR组合关系 runAfterEither
 *
 * @author lyx
 * @date 2023-02-18
 **/
public class RunAfterEither {
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



        CompletableFuture<Void> future = future01.runAfterEitherAsync(future02, () -> {
            System.out.println("任务3开始...");
        }, executor);

    }
}

package com.gangbb.concurrency.completableFuture.and;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试AND组合关系 thenCombine
 *
 * @author lyx
 * @date 2023-02-18
 **/
public class ThenCombine {

    public static void main(String[] args) {
        //创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        //开启异步任务1
        CompletableFuture<Integer> task = CompletableFuture.supplyAsync(() -> {
            System.out.println("异步任务1，当前线程是：" + Thread.currentThread().getId());
            int result = 1 + 1;
            System.out.println("异步任务1结束");
            return result;
        }, executorService);

        //开启异步任务2
        CompletableFuture<Integer> task2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("异步任务2，当前线程是：" + Thread.currentThread().getId());
            int result = 1 + 1;
            System.out.println("异步任务2结束");
            return result;
        }, executorService);

        //任务组合
        CompletableFuture<Integer> task3 = task.thenCombineAsync(task2, (f1, f2) -> {
            System.out.println("执行任务3，当前线程是：" + Thread.currentThread().getId());
            System.out.println("任务1返回值：" + f1);
            System.out.println("任务2返回值：" + f2);
            return f1 + f2;
        }, executorService);

        Integer res = null;
        try {
            res = task3.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("最终结果：" + res);
    }
}

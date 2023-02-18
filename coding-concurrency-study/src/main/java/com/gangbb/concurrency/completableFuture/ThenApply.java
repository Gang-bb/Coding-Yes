package com.gangbb.concurrency.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 测试CompletableFuture的ThenApply
 *
 * @author lyx
 * @date 2023-02-16
 **/
public class ThenApply {
    public static void main(String[] args) {
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("第一个线程启动。所属：" + Thread.currentThread().getThreadGroup());
            return 666;
        }).thenApply(res -> {
            System.out.println("第二个线程启动。所属：" + Thread.currentThread().getThreadGroup());
            System.out.println("获取第一个线程结果:" + res);
            return "第二线程获取第一个线程结果后再处理的结果：" + (res + 999);
        });

        try {
            System.out.println(stringCompletableFuture.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

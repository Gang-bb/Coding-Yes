package com.gangbb.concurrency.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 测试CompletableFuture的ThenAccept
 *
 * @author lyx
 * @date 2023/2/15
 **/
public class ThenAccept {
    public static void main(String[] args) {
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("第一个线程启动。所属：" + Thread.currentThread().getThreadGroup());
            return 666;
        }).thenAccept(res -> {
            System.out.println("第二个线程启动。所属：" + Thread.currentThread().getThreadGroup());
            System.out.println("获取第一个线程结果:" + res);
        });
        try {
            System.out.println("res:" + voidCompletableFuture.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

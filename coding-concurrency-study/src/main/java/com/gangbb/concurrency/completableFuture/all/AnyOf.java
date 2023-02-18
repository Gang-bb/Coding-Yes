package com.gangbb.concurrency.completableFuture.all;

import java.util.concurrent.*;

/**
 * 多任务的编排 anyOf
 *
 * @author lyx
 * @date 2023-02-18
 **/
public class AnyOf {
    public static void main(String[] args) {
        ThreadFactory threadFactory = r -> new Thread(r, "MyPool-Thread-" + r.hashCode());
        ExecutorService executor = Executors.newFixedThreadPool(10, threadFactory);


        CompletableFuture<String> futureImg = CompletableFuture.supplyAsync(() -> {
            System.out.println("查询商品图片信息");
            return "hello.jpg";
        },executor);

        CompletableFuture<String> futureAttr = CompletableFuture.supplyAsync(() -> {
            System.out.println("查询商品属性信息");
            return "黑色+256G";
        },executor);

        CompletableFuture<String> futureDesc = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
                System.out.println("查询商品介绍信息");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "华为...";
        },executor);

        try {
            //等待所有结果完成
            CompletableFuture<Object> anyOf = CompletableFuture.anyOf(futureAttr, futureImg, futureDesc);
            System.out.println(anyOf.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

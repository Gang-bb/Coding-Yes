package com.gangbb.concurrency.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 测试CompletableFuture的Exceptionally
 *
 * @author lyx
 * @date 2023-02-16
 **/
public class Exceptionally {

    public static void main(String[] args) {
        CompletableFuture<Double> future = CompletableFuture.supplyAsync(() -> {

            if (Math.random() < 0.5) {
                throw new RuntimeException("出错了");
            }
            System.out.println("正常结束");
            return 0.11;

        }).exceptionally((throwable) -> {
            if (throwable == null) {
                System.out.println("whenComplete throwable is null");
            } else {
                System.out.println("whenComplete throwable is " + throwable.getMessage());
            }
            return 520.0;
        });
        try {
            System.out.println("最终返回的结果 = " + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

package com.gangbb.concurrency.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 测试CompletableFuture的Handle2
 *
 * @author lyx
 * @date 2023-02-17
 **/
public class Handle2 {
    public static void main(String[] args) {
        CompletableFuture<Double> future = CompletableFuture.supplyAsync(() -> {
            if (Math.random() < 0.5) {
                throw new RuntimeException("出错了");
            }
            System.out.println("正常结束");
            return 0.11;

        }).handle((aDouble, throwable) -> {
            System.out.println("handle的aDouble：" + aDouble);
            System.out.println("handle的throwable：" + throwable);
            return 0.22;
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

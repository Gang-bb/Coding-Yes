package com.gangbb.concurrency.completableFuture;

import com.gangbb.concurrency.future.FutureAndCountDownLatch;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 优化 (Future + CountDownLatch)实现异步任务需要相互依赖的场景 {@link FutureAndCountDownLatch}
 *
 * @author lyx
 * @date 2023/2/14
 **/
public class DoCompletableInfo {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        //调用用户服务获取用户基本信息
        CompletableFuture<String> userFuture = CompletableFuture.supplyAsync(() ->
                //模拟查询用户耗时500毫秒
        {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //throw new RuntimeException("错误");
            return "用户A";
        });

        //调用商品服务获取商品基本信息
        CompletableFuture<String> goodsFuture = CompletableFuture.supplyAsync(() ->
                //模拟查询商品耗时600毫秒
        {
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "商品A";
        });

        try {
            //模拟主程序耗时时间
            Thread.sleep(700);
            System.out.println("获取用户信息:" + userFuture.get());
            System.out.println("获取商品信息:" + goodsFuture.get());
            System.out.println("总共用时" + (System.currentTimeMillis() - startTime) + "ms");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}

package com.gangbb.concurrency.future;

import java.util.concurrent.*;

/**
 * Future + CountDownLatch： 异步任务需要相互依赖的场景
 * (Future JDK5新增)
 * (Java8 中可以用CompletableFuture 优化)
 *
 * @author lyx
 * @date 2023/2/14
 **/
public class FutureAndCountDownLatch {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
        CountDownLatch countDownLatch = new CountDownLatch(2);

        Future<String> userInfo = fixedThreadPool.submit(() -> {
            // 模拟查询用户
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
            return "用户A";
        });


        Future<String> goodsInfo = fixedThreadPool.submit(() -> {
            // 模拟查询商品
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
            return "商品A";
        });

        try {
            countDownLatch.await();
            Thread.sleep(700);
            System.out.println("获取用户信息:" + userInfo.get());
            System.out.println("获取商品信息:" + goodsInfo.get());
            System.out.println("总共用时" + (System.currentTimeMillis() - startTime) + "ms");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }catch (ExecutionException e) {
            e.printStackTrace();
        }
        fixedThreadPool.shutdown();
    }
}

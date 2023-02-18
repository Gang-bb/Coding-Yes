package com.gangbb.concurrency.completableFuture;


import java.util.concurrent.*;

/**
 * 结果方法测试
 *
 * @author lyx
 * @date 2023/2/15
 **/
public class CompletableGetResult {
    public static void main(String[] args) {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务开始休眠1S");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务休眠结束");
            return 1 / 0;
        });
        // get()会阻塞
        // get(long timeout, TimeUnit unit) 超时会有TimeoutException。 中断执行
//        try {
//            System.out.println("get()：" + completableFuture.get(1, TimeUnit.SECONDS));
//            System.out.println("get()：" + completableFuture.get());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }catch (TimeoutException e) {
//            e.printStackTrace();
//        }

        // getNow()
//        try {
//            Thread.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("getNow()：" + completableFuture.getNow(2));


        // join()
        try {
            System.out.println("get()：" + completableFuture.join());
        } catch (CompletionException e) {
            System.out.println("join()得到任务出错");
            e.printStackTrace();
        }
    }
}

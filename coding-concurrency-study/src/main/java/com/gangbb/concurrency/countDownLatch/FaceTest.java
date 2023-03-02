package com.gangbb.concurrency.countDownLatch;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 人脸识别测试
 *
 * @author lyx
 * @date 2023-03-02
 **/
public class FaceTest {

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        AtomicBoolean condition = new AtomicBoolean(true);
        for (Integer integer : list) {
            if(!condition.get()){
                break;
            }
            executorService.execute(() -> {
                // 模拟人脸识别
                try {
                    System.out.println(integer + "开始识别");
                    Thread.sleep(integer*1000);
                    System.out.println(integer + "识别结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(integer == 1){
                    countDownLatch.countDown();
                    condition.set(false);
                    //throw new RuntimeException("错误");
                }
            });
        }
        try {
            boolean await = countDownLatch.await(2, TimeUnit.SECONDS);
            System.out.println("await:" + await);
            if(await){
                System.out.println("成功处理");
            }else {
                System.out.println("过期没处理完");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            executorService.shutdownNow();
        }
        System.out.println("结束");
        System.out.println(executorService.isShutdown());
        System.out.println(executorService.isTerminated());
    }
}

package com.gangbb.concurrency.countDownLatch;

import cn.hutool.core.collection.CollUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * 模拟场景：针对用户批量下载的文件进行修改上传
 *
 * @author lyx
 * @date 2023-02-26
 **/
public class DoFileDemo {

    public static void main(String[] args) {
         // 下载文件总数
         List<Integer> resultList = new ArrayList<>(100);
         IntStream.range(0,100).forEach(resultList::add);
         // 下载文件分段
         List<List<Integer>> split = CollUtil.split(resultList, 10);
         // 设置参数。模拟线上bug
         ExecutorService executorService = new ThreadPoolExecutor(2, 2,
                 0, TimeUnit.SECONDS, new LinkedBlockingQueue<>(100));
         CountDownLatch countDownLatch = new CountDownLatch(100);
         for (List<Integer> list : split) {
           executorService.execute(() -> {
             list.forEach(i ->{
               try {
                 // 模拟业务操作
                 Thread.sleep(500);
                 System.out.println("任务进入");
               } catch (InterruptedException e) {
                 e.printStackTrace();
                 System.out.println(e.getMessage());
               } finally {
                 System.out.println(countDownLatch.getCount());
                 countDownLatch.countDown();
               }
             });
           });
         }
         try {
           countDownLatch.await(1, TimeUnit.SECONDS);
           System.out.println("countDownLatch.await()");
         } catch (InterruptedException e) {
           e.printStackTrace();
         }
   }
}

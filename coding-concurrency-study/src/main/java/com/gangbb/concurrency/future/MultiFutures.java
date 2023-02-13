package com.gangbb.concurrency.future;


import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;

/**
 * 多个任务，用Future数组来获取结果
 *
 * @author lyx
 * @date 2023/2/13
 **/
public class MultiFutures {
    public static void main(String[] args) {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(20);
        ArrayList<Future<Integer>> list = new ArrayList<>(20);

        for (int i = 0; i < 20; i++) {
            Future<Integer> future = fixedThreadPool.submit(new CallableTask());
            list.add(future);
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 20; i++) {
            Future<Integer> future = list.get(i);
            try {
                Integer integer = future.get();
                System.out.println(integer);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }


    static class CallableTask implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            int nextInt = new Random().nextInt();
            Thread.sleep(2);
            return nextInt;
        }
    }
}

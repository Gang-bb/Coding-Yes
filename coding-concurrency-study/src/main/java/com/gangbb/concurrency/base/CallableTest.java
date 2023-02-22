package com.gangbb.concurrency.base;

import java.util.concurrent.*;

/**
 * Java实现多线程方法--实现Callable接口
 *
 * @author lyx
 * @date 2023/2/9
 **/
public class CallableTest {
    public static void main(String[] args) {
        Task task = new Task();
        FutureTask<Integer> ft = new FutureTask<>(task);
        Thread thread = new Thread(ft);
        thread.start();

        // 线程池启动
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.submit(ft);
        try {
            System.out.println(ft.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();

    }

    static class Task implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            for (int i = 0; i < 20; i++) {
                System.out.println("task1线程运行" + Thread.currentThread().getName());
                System.out.println("Task11实现Callable接口实现多线程");
            }
            return 1;
        }
    }
}

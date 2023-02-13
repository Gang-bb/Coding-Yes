package com.gangbb.concurrency.future;

import java.util.concurrent.*;

/**
 * Future类的使用demo
 *
 * @author lyx
 * @date 2023/2/13
 **/
public class OneFuture {
    public static void main(String[] args) {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
        Future<Integer> future = fixedThreadPool.submit(new Task());
        try {

            System.out.println("get()1：" + future.get());
            System.out.println("isDone()：" + future.isDone());
            System.out.println("isCancelled()：" + future.isCancelled());
            System.out.println("get()2：" + future.get());
            System.out.println("cancel()：" + future.cancel(false));
            System.out.println("isCancelled()2：" + future.isCancelled());
            System.out.println("get()3：" + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        fixedThreadPool.shutdown();
    }
}

class Task implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        // 模拟处理任务
        for (int i = 0; i < 10; i++) {
            TimeUnit.SECONDS.sleep(2);
            System.out.println("task处理任务中：" + i);
        }


        return 6666;
    }
}

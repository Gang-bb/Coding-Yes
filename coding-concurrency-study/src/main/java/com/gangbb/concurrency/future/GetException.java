package com.gangbb.concurrency.future;

import java.util.concurrent.*;

/**
 * 演示get方法过程中抛出异常，for循环为了演示抛出Exception的时机：并不是说一产生异常就抛出，直到我们get执行时，才会抛出。
 *
 * @author lyx
 * @date 2023/2/13
 **/
public class GetException {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future<Integer> future = executorService.submit(new TaskException());

        try {
            // 模拟处理业务
            for (int i = 0; i < 5; i++) {
                System.out.println(i);
                Thread.sleep(500);
            };
            System.out.println("isDone()：" + future.isDone());
            Integer integer = future.get();
        }catch (ExecutionException e){
            // 调用get才会有这个异常
            e.printStackTrace();
            System.out.println("ExecutionException异常");
        }
        catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("InterruptedException异常");
        }

    }

    static class TaskException implements Callable{

        @Override
        public Object call() throws Exception {
            throw new IllegalArgumentException("Callable抛出异常");
        }
    }
}

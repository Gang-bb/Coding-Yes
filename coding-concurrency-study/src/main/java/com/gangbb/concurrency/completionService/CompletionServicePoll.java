package com.gangbb.concurrency.completionService;

import java.util.concurrent.*;

/**
 * TODO:deprecated
 *
 * @author lyx
 * @date 2023-02-24
 **/
public class CompletionServicePoll {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ExecutorCompletionService<Object> completionService = new ExecutorCompletionService<>(executorService);
        completionService.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                Thread.sleep(3000);
                System.out.println("3秒过后了");
                return "小石潭记 3s";
            }
        });

        System.out.println(completionService.poll(4, TimeUnit.SECONDS));
    }
}

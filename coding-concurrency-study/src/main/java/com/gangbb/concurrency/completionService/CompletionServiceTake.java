package com.gangbb.concurrency.completionService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * CompletionService take 测试
 *
 * @author lyx
 * @date 2023-02-24
 **/
public class CompletionServiceTake {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        MyCallable callable1 = new MyCallable("username1", 5000);
        MyCallable callable2 = new MyCallable("username2", 4000);
        MyCallable callable3 = new MyCallable("username3", 3000);
        MyCallable callable4 = new MyCallable("username4", 2000);
        MyCallable callable5 = new MyCallable("username5", 1000);
        List<Callable> callableList = new ArrayList<>(5);
        callableList.add(callable1);
        callableList.add(callable2);
        callableList.add(callable3);
        callableList.add(callable4);
        callableList.add(callable5);

        ThreadPoolExecutor executor = new ThreadPoolExecutor(5,
                10, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

        ExecutorCompletionService completionService = new ExecutorCompletionService<>(executor);
        for (int i = 0; i < 5; i++) {
            completionService.submit(callableList.get(i));
        }

        for (int i = 0; i < 5; i++) {
            //System.out.println("等待打印第" + (i + 1) + "个返回值");
            //System.out.println(completionService.take().get());
            Future poll = completionService.poll(1, TimeUnit.MILLISECONDS);
            if(poll != null){
                System.out.println(poll.get());
            }

        }
        System.out.println("over");
        executor.shutdown();
    }
}

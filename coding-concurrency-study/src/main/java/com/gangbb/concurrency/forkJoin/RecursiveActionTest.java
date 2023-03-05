package com.gangbb.concurrency.forkJoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

/**
 * RecursiveAction
 *
 * @author lyx
 * @date 2023-03-05
 **/
public class RecursiveActionTest extends RecursiveAction {

    /**
     *  每个"小任务"最多只打印20个数
     */
    private static final int MAX = 20;

    private int start;
    private int end;

    public RecursiveActionTest(int start, int end) {
        this.start = start;
        this.end = end;
    }


    /**
     * The main computation performed by this task.
     */
    @Override
    protected void compute() {
        //当end-start的值小于MAX时，开始打印
        if((end-start) < MAX) {
            for(int i= start; i<end;i++) {
                System.out.println(Thread.currentThread().getName()+"i的值"+i);
            }
        }else {
            // 将大任务分解成两个小任务
            int middle = (start + end) / 2;
            RecursiveActionTest left = new RecursiveActionTest(start, middle);
            RecursiveActionTest right = new RecursiveActionTest(middle, end);
            left.fork();
            right.fork();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 创建包含Runtime.getRuntime().availableProcessors()返回值作为个数的并行线程的ForkJoinPool
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        // 提交可分解的PrintTask任务
        forkJoinPool.submit(new RecursiveActionTest(0, 1000));

        //阻塞当前线程直到 ForkJoinPool 中所有的任务都执行结束
        forkJoinPool.awaitTermination(2, TimeUnit.SECONDS);

        System.out.println("活跃线程数："+forkJoinPool.getActiveThreadCount());
        System.out.println("窃取任务数："+forkJoinPool.getStealCount());

        // 关闭线程池
        forkJoinPool.shutdown();
    }
}

package com.gangbb.concurrency.forkJoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * ForkJoin 使用示例
 * 包含1.7和1.8的使用示例
 *
 * @author lyx
 * @date 2023-02-22
 **/
public class ForkJoinTest {
    public static void main(String[] args) {
        // jdk7的写法
        long startTime = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinCalculate forkJoinCalculate = new ForkJoinCalculate(0, 1000000000L);
        Long res = forkJoinPool.invoke(forkJoinCalculate);
        System.out.println("jdk7 result " + res + " cost " + (System.currentTimeMillis() - startTime));

        // jdk8的写法
        long start = System.currentTimeMillis();
        // 只有并行流才会使用fork/join框架，否则就是单线程执行
        long sum = LongStream.rangeClosed(0, 1000000000L).parallel().sum();
        System.out.println("jdk8 parallel exe result " + sum + " cost " + (System.currentTimeMillis() - start));
    }

    private static class ForkJoinCalculate extends RecursiveTask<Long> {

        private long start;

        private long end;

        private static final long THRESHOLD = 10000;

        public ForkJoinCalculate(long start, long end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            if (end - start <= THRESHOLD) {
                long sum = 0;
                for (long i = start; i <= end; i++) {
                    sum += i;
                }
                return sum;
            } else {
                long middle = (start + end) / 2;
                ForkJoinCalculate leftFork = new ForkJoinCalculate(start, middle);
                // 把任务分配到异步线程池中
                leftFork.fork();
                ForkJoinCalculate rightFork = new ForkJoinCalculate(middle + 1, end);
                // 把任务分配到异步线程池中
                rightFork.fork();
                // 把结果合并
                return leftFork.join() + rightFork.join();
            }
        }
    }
}

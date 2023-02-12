package com.gangbb.concurrency.threadPool;

import java.util.concurrent.*;

/**
 * 创建线程池
 *
 * @author lyx
 * @date 2023/2/12
 **/
public class CreatThreadPool {
    public static void main(String[] args) {
        // Executors创建
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(10);
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        ExecutorService workStealingPool = Executors.newWorkStealingPool();
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

        // 线程池类ThreadPoolExecutor
        /*- `corePoolSize`：核心线程数。如果等于0，则任务执行完后，没有任务请求进入时销毁线程池中的线程。如果大于0，即使本地任务执行完毕，核心线程也不会被销毁。设置过大会浪费系统资源，设置过小导致线程频繁创建。

            - `maximumPoolSize`：最大线程数。必须大于等于1，且大于等于corePoolSize。如果与corePoolSize相等，则线程池大小固定。如果大于corePoolSize，则最多创建maximumPoolSize个线程执行任务

            - `keepAliveTime`：线程空闲时间。线程池中线程空闲时间达到keepAliveTime值时，线程会被销毁，只到剩下corePoolSize个线程为止。默认情况下，线程池的最大线程数大于corePoolSize时，keepAliveTime才会起作用。如果allowCoreThreadTimeOut被设置为true，即使线程池的最大线程数等corePoolSize，keepAliveTime也会起作用（回收超时的核心线程）。

            - `unit`：TimeUnit表示时间单位。

            - `workQueue`：缓存队列。当请求线程数大于corePoolSize时，线程进入BlockingQueue阻塞队列。

            - `threadFactory`：线程工厂。用来生产一组相同任务的线程。主要用于设置生成的线程名词前缀、是否为守护线程以及优先级等。设置有意义的名称前缀有利于在进行虚拟机分析时，知道线程是由哪个线程工厂创建的。

            - `handler`：执行拒绝策略对象。当达到任务缓存上限时（即超过workQueue参数能存储的任务数），执行拒接策略，可以看作简单的限流保护。
            */
        ThreadFactory threadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "test-" + r.hashCode());
            }
        };
        LinkedBlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<>(2000);
        new ThreadPoolExecutor(5, 10,
                60, TimeUnit.SECONDS, blockingQueue, threadFactory);
    }
}

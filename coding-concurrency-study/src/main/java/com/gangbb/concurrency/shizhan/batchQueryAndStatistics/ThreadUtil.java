package com.gangbb.concurrency.shizhan.batchQueryAndStatistics;

import java.util.concurrent.*;

/**
 * 线程工具类
 *
 * @author lyx
 * @date 2023-02-24
 **/
public class ThreadUtil {

    private volatile static ThreadUtil threadUtil;

    private ThreadPoolExecutor executor;

    private ThreadUtil() {
    }

    public static ThreadUtil getThreadUtilInstance() {
        if (null == threadUtil) {
            synchronized (ThreadUtil.class) {
                if (null == threadUtil) {
                    threadUtil = new ThreadUtil();
                }
            }
        }
        return threadUtil;
    }

    /**
     * 提交任务
     *
     * @param task
     */
    public Future<?> submit(Runnable task) {
        if (executor == null) {
            // 初始化线程池
            executor = initialize();
        }
        // 执行线程
        return executor.submit(task);
    }

    /**
     * 初始化线程池
     *
     * @return
     */
    private synchronized ThreadPoolExecutor initialize() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        return new Thread(r, "ThreadUtil-pool-" + r.hashCode());
                    }
                },
                new ThreadPoolExecutor.CallerRunsPolicy());
        System.out.println("===================>ThreadUtil线程池初始化");
        return executor;
    }

    /**
     * 关闭线程池
     */
    public void shutdown() {
        if (executor != null) {
            executor.shutdown();
        }
    }
}

package com.gangbb.concurrency.rejectedHandler;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 自定义拒绝策略
 *
 * @author lyx
 * @date 2023-02-23
 **/
public class MyRejectedExecutionHandler implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            // doSomeThing
    }
}

package com.gangbb.multithreading.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Gangbb
 * @date 2021/3/30 18:18
 * @Description: 测试使用ScheduledThreadPool
 */
public class TestScheduledThreadPool {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        // 参数1.执行的任务  2.延迟的时间  3.延迟时间的单位
        //scheduledExecutorService.schedule(new Task(), 5, TimeUnit.SECONDS);
        // 以一定频率运行 参数1.执行的任务  2.初始运行延迟时间  3.运行延迟周期 4.延迟时间的单位
        scheduledExecutorService.scheduleAtFixedRate(new Task(), 1,3,TimeUnit.SECONDS);
    }
}

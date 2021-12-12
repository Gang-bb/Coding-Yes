package com.gangbb.schedule.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 测试Scheduling
 *
 * @author Liangyixiang
 * @date 2021/11/29
 **/
@Component
public class SchedulingService {

    /**
     * 两秒执行一次该任务
     *
     * @return void
     * @author Liangyixiang
     * @date 2021/11/29
     **/
    @Scheduled(fixedRate = 2000)
    public void test(){
        System.out.println("hello");
    }
}
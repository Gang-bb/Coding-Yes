package com.test.demo.controller;

import com.test.demo.service.CglibTestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @Author LiangYiXiang
 * @Description 测试 异步调用controller
 * @Date 2021/11/14
 **/
@RestController
public class AsyncTransferController {

    /**
     * 全局日志打印log
     */
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 获取ApplicationContext对象方式有多种,这种最简单,其它的大家自行了解一下
     */
    @Autowired
    private ApplicationContext applicationContext;

    @RequestMapping(value = "/applicationContext/test", method = GET)
    @ResponseBody
    public Map<String, Object> applicationContextTest () {
        Map<String, Object> resMap = new HashMap<String, Object>();
        try{
            //测试一： 这样调用同类下的异步方法是不起作用的
            this.testAsyncTask1();
            this.testAsyncTask2();

            //测试二：通过上下文获取自己的代理对象调用异步方法
//            AsyncTransferController asyncTransferController = (AsyncTransferController)applicationContext.getBean(AsyncTransferController.class);
//            asyncTransferController.testAsyncTask1();
//            asyncTransferController.testAsyncTask2();

            resMap.put("code",200);
        }catch (Exception e) {
            resMap.put("code",400);
            log.error("error!",e);
        }
        return resMap;
    }

    @Autowired
    private CglibTestService cglibTestService;

    @RequestMapping(value = "/cglib/test", method = GET)
    @ResponseBody
    public Map<String, Object> cglibTest () {
        Map<String, Object> resMap = new HashMap<String, Object>();
        try{
            AsyncTransferController asyncTransferController = (AsyncTransferController) AopContext.currentProxy();
            asyncTransferController.testAsyncTask1();
            asyncTransferController.testAsyncTask2();

            resMap.put("code",200);
        }catch (Exception e) {
            resMap.put("code",400);
            log.error("error!",e);
        }
        return resMap;
    }


    @RequestMapping(value = "/AsyncConfig/test", method = GET)
    @ResponseBody
    public Map<String, Object> asyncConfigTest () {
        System.out.println("主线程：" + Thread.currentThread().getName());
        Map<String, Object> resMap = new HashMap<String, Object>();
        try{
            AsyncTransferController asyncTransferController = (AsyncTransferController) AopContext.currentProxy();
            asyncTransferController.testAsyncTask3();

            resMap.put("code",200);
        }catch (Exception e) {
            resMap.put("code",400);
            log.error("error!",e);
        }
        return resMap;
    }


    /**
     * 测试异步方法1(注意一定是public,且是非static方法)
     *
     * @return void
     * @author Liangyixiang
     * @date 2021/11/14
     **/
    @Async
    public void testAsyncTask1() throws InterruptedException {
        System.out.println("内部线程：" + Thread.currentThread().getName());
        System.out.println("开始执行任务1");
        long start = System.currentTimeMillis();
        Thread.sleep(2000);
        long end = System.currentTimeMillis();
        System.out.println("完成任务一，耗时：" + (end - start) + "毫秒");
    }

    /**
     * 测试异步方法2(注意一定是public,且是非static方法)
     *
     * @return void
     * @author Liangyixiang
     * @date 2021/11/14
     **/
    @Async
    public void testAsyncTask2() throws InterruptedException {
        System.out.println("内部线程：" + Thread.currentThread().getName());
        System.out.println("开始执行任务2");
        long start = System.currentTimeMillis();
        Thread.sleep(2000);
        long end = System.currentTimeMillis();
        System.out.println("完成任务二，耗时：" + (end - start) + "毫秒");
    }

    /**
     * 使用自定义线程池异步方法
     *
     * @return void
     * @author Liangyixiang
     * @date 2021/11/14
     **/
    @Async("customExecutor")
    public void testAsyncTask3() throws InterruptedException {
        System.out.println("内部线程：" + Thread.currentThread().getName());
        System.out.println("开始执行任务2");
        long start = System.currentTimeMillis();
        Thread.sleep(2000);
        long end = System.currentTimeMillis();
        System.out.println("完成任务二，耗时：" + (end - start) + "毫秒");
    }

}
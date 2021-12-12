package com.test.demo.service;

import com.test.demo.controller.AsyncTransferController;
import org.springframework.aop.framework.AopContext;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author LiangYiXiang
 * @Description Cglib代理测试
 * @Date 2021/11/14
 **/
@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class CglibTestService {

    @Autowired
    private ApplicationContext applicationContext;

    @Async
    public void testSyncTask() throws InterruptedException {
        Thread.sleep(10000);
        System.out.println("异步任务执行完成！");
    }


    public void asyncCallTwo() throws InterruptedException {
        //this.testSyncTask();

//        CglibTestService cglibTestService = (CglibTestService)applicationContext.getBean(CglibTestService.class);
//        cglibTestService.testSyncTask();

        //是否是代理对象
        boolean isAop = AopUtils.isAopProxy(AsyncTransferController.class);
        //是否是CGLIB方式的代理对象
        boolean isCglib = AopUtils.isCglibProxy(AsyncTransferController.class);
        //是否是JDK动态代理方式的代理对象
        boolean isJdk = AopUtils.isJdkDynamicProxy(AsyncTransferController.class);
        //以下才是重点!!!
        CglibTestService cglibTestService = (CglibTestService)applicationContext.getBean(CglibTestService.class);
        CglibTestService proxy = (CglibTestService) AopContext.currentProxy();
        System.out.println(cglibTestService == proxy ? true : false);
        proxy.testSyncTask();
        System.out.println("end!!!");
    }
}
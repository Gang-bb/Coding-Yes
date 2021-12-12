package com.gangbb.activity;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Author 梁艺翔
 * @Description TODO
 * @Date 2021/9/17
 **/
@SpringBootTest
public class Part3_ProcessInstance {

    @Autowired
    private RuntimeService runtimeService;


    /**
     * @Author Liangyixiang
     * @Description 初始化流程实例
     * @Date 2021/9/17
     **/
    @Test
    public void initProcessInstance(){
        //1、获取页面表单填报的内容，例如：请假时间，请假事由 --> fromData
        //2、fromData 写入业务表，返回业务表主键ID==businessKey
        //3、把业务数据与Activiti7流程数据关联
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess_claim","bKey003");
        System.out.println("流程实例ID："+processInstance.getProcessDefinitionId());

    }

    /**
     * @Author Liangyixiang
     * @Description 获取流程实例列表
     * @Date 2021/9/17
     **/
    @Test
    public void getProcessInstances(){
        List<ProcessInstance> list = runtimeService.createProcessInstanceQuery().list();
        for(ProcessInstance pi : list){
            System.out.println("--------流程实例------");
            System.out.println("ProcessInstanceId："+pi.getProcessInstanceId());
            System.out.println("ProcessDefinitionId："+pi.getProcessDefinitionId());
            System.out.println("isEnded"+pi.isEnded());
            System.out.println("isSuspended："+pi.isSuspended());

        }

    }


    /**
     * @Author Liangyixiang
     * @Description 暂停与激活流程实例
     * @Date 2021/9/17
     **/
    @Test
    public void activitieProcessInstance(){
         runtimeService.suspendProcessInstanceById("78374156-1767-11ec-bf3f-d8f3bceac44a");
        System.out.println("挂起流程实例");

//        runtimeService.activateProcessInstanceById("78374156-1767-11ec-bf3f-d8f3bceac44a");
//        System.out.println("激活流程实例");
    }

    /**
     * @Author Liangyixiang
     * @Description 删除流程实例
     * @Date 2021/9/17
     **/
    @Test
    public void delProcessInstance(){
        runtimeService.deleteProcessInstance("78374156-1767-11ec-bf3f-d8f3bceac44a","删着玩");
        System.out.println("删除流程实例");
    }
}
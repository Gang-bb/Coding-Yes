package com.gangbb.activity;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
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
public class Part4_Task {

    @Autowired
    private TaskService taskService;

    /**
     * @Author Liangyixiang
     * @Description 任务查询(一般管理员使用,督促任务)
     * @Date 2021/9/17
     **/
    @Test
    public void getTasks(){
        List<Task> list = taskService.createTaskQuery().list();
        for(Task tk : list){
            System.out.println("Id："+tk.getId());
            System.out.println("Name："+tk.getName());
            System.out.println("Assignee："+tk.getAssignee());
        }
    }

    /**
     * @Author Liangyixiang
     * @Description 查看我的待办任务
     * @Date 2021/9/17
     **/
    @Test
    public void getTasksByAssignee(){
        List<Task> list = taskService.createTaskQuery()
                .taskAssignee("wukong")
                .list();
        for(Task tk : list){
            System.out.println("Id："+tk.getId());
            System.out.println("Name："+tk.getName());
            System.out.println("Assignee："+tk.getAssignee());
        }

    }

    /**
     * @Author Liangyixiang
     * @Description 执行任务
     * @Date 2021/9/17
     **/
    @Test
    public void completeTask(){
        taskService.complete("4d7b8f0a-176d-11ec-bbfa-d8f3bceac44a");
        System.out.println("完成任务");

    }

    //拾取任务
    @Test
    public void claimTask(){
        // Activity7以前的写法(未引入Spring Security)
//        List<Task> list = taskService.createTaskQuery()
//                .taskCandidateUser("bajie")
//                .list();

        Task task = taskService.createTaskQuery().taskId("b4006a29-176e-11ec-8b27-d8f3bceac44a").singleResult();
        taskService.claim("b4006a29-176e-11ec-8b27-d8f3bceac44a","bajie");
    }

    //归还与交办任务
    @Test
    public void setTaskAssignee(){
        Task task = taskService.createTaskQuery().taskId("b4006a29-176e-11ec-8b27-d8f3bceac44a").singleResult();
        taskService.setAssignee("b4006a29-176e-11ec-8b27-d8f3bceac44a","null");//归还候选任务
        taskService.setAssignee("b4006a29-176e-11ec-8b27-d8f3bceac44a","wukong");//交办
    }
}
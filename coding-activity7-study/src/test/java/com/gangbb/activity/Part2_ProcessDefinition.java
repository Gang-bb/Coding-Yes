package com.gangbb.activity;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
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
public class Part2_ProcessDefinition {

    @Autowired
    private RepositoryService repositoryService;

    /**
     * @Author Liangyixiang
     * @Description 查询流程定义
     * @Date 2021/9/17
     **/
    @Test
    public void getDefinitions(){
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery()
                .list();
        for(ProcessDefinition pd : list){
            System.out.println("------流程定义--------");
            System.out.println("Name："+pd.getName());
            System.out.println("Key："+pd.getKey());
            System.out.println("ResourceName："+pd.getResourceName());
            System.out.println("DeploymentId："+pd.getDeploymentId());
            System.out.println("Version："+pd.getVersion());

        }

    }

    /**
     * @Author Liangyixiang
     * @Description 删除流程定义
     * @Date 2021/9/17
     **/
    @Test
    public void delDefinition(){

        String pdID="ba863ada-1759-11ec-b63e-d8f3bceac44a";
        // true:会删除所有流程实例、任务以及历史；false:会保留历史。实际项目使用false
        repositoryService.deleteDeployment(pdID,true);
        System.out.println("删除流程定义成功");
    }
}
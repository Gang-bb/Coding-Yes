package com.gangbb.activity;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

/**
 * @Author 梁艺翔
 * @Description Part1_Deployment 测试代码
 * @Date 2021/9/17
 **/
@SpringBootTest
public class Part1_Deployment {

    @Autowired
    private RepositoryService repositoryService;

    /**
     * @Author Liangyixiang
     * @Description 通过BPMN部署
     * @Date 2021/9/17
     **/
    @Test
    public void initDeploymentBPMN(){
        String filename = "BPMN/Part4_Task_claim.bpmn";
        //String pngName="BPMN/Part1_Deployment.png";
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource(filename)
                //.addClasspathResource(pngName)//图片
                .name("流程部署测试候选人TASK")
                .deploy();
        System.out.println(deployment.getName());
    }

    /**
     * @Author Liangyixiang
     * @Description 通过ZIP部署流程
     * @Date 2021/9/17
     **/
    @Test
    public void initDeploymentZIP() {
        InputStream fileInputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("BPMN/Part1_DeploymentV2.zip");
        ZipInputStream zip=new ZipInputStream(fileInputStream);
        Deployment deployment=repositoryService.createDeployment()
                .addZipInputStream(zip)
                .name("流程部署测试zip")
                .deploy();
        System.out.println(deployment.getName());
    }


    /**
     * @Author Liangyixiang
     * @Description 查询部署流程
     * @Date 2021/9/17
     **/
    @Test
    public void getDeployments() {
        // 获取所有部署过的流程
        List<Deployment> list = repositoryService.createDeploymentQuery().list();
        for(Deployment dep : list){
            System.out.println("Id："+dep.getId());
            System.out.println("Name："+dep.getName());
            System.out.println("DeploymentTime："+dep.getDeploymentTime());
            System.out.println("Key："+dep.getKey());
        }

    }
}
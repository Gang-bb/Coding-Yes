package com.gangbb.batchtest.controller;

import com.gangbb.batchtest.mapper.BatchTestMapper;
import com.gangbb.batchtest.model.entity.BatchTest;
import com.gangbb.batchtest.service.IBatchTestService;
import com.gangbb.batchtest.utils.InsertConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 测试批量插入
 *
 * @author Liangyixiang
 * @date 2021/11/26
 **/
@RestController
public class BatchController {

    @Autowired
    private IBatchTestService batchTestService;

    @Autowired
    private BatchTestMapper batchTestMapper;


    @PostMapping("/test/batch")
    public String insertData(@RequestParam int size) {

        List<BatchTest> list = getList(size);
        long start = System.currentTimeMillis();
        // mp原生新增
        //batchTestService.saveBatch(list);
        // plus大佬封装 新增
        //batchTestMapper.insertAll(list);
        // 并行流使用示例1
        InsertConsumer.insertData(list, batchTestService::saveBatch);
        // 并行流使用示例2
        InsertConsumer.insertData(list, batchTestMapper::insertAll);
        System.out.println("插入 " + size +" 条数据 耗时："+(System.currentTimeMillis()-start));

        return "ApiResult.success()";
    }

    /**
     * 模拟BatchTest数据
     *
     * @param size 创建条数
     * @return List<BatchTest>
     * @author Liangyixiang
     * @date 2021/11/26
     **/
    private List<BatchTest> getList(int size){
        long start = System.currentTimeMillis();
        List<BatchTest> batchTestList = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            BatchTest batchTest = new BatchTest();
            //batchTest.setId((long) i);
            batchTest.setRelationId(121L);
            batchTest.setParam1("Param1："+ i);
            batchTest.setParam2("Param2："+ i);
            batchTest.setTextParam("TextParam："+ i);
            batchTest.setDelFlag((byte)0);
            batchTest.setCreateBy(1L);
            batchTest.setCreateTime(new Date());
            batchTest.setRemark("xxxxx");
            batchTestList.add(batchTest);
        }
        System.out.println("拼装数据 耗时："+(System.currentTimeMillis()-start));
        return batchTestList;
    }
}
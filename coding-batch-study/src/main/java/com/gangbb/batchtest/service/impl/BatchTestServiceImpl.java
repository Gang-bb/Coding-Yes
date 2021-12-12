package com.gangbb.batchtest.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gangbb.batchtest.mapper.BatchTestMapper;
import com.gangbb.batchtest.model.entity.BatchTest;
import com.gangbb.batchtest.service.IBatchTestService;
import org.springframework.stereotype.Service;

/**
 * BatchTest service实现类
 *
 * @author Liangyixiang
 * @date 2021/11/26
 **/
@Service
public class BatchTestServiceImpl extends ServiceImpl<BatchTestMapper, BatchTest> implements IBatchTestService {
}
package com.gangbb.batchtest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gangbb.batchtest.model.entity.BatchTest;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;

/**
 * BatchTest mapper
 *
 * @author Liangyixiang
 * @date 2021/11/26
 **/
public interface BatchTestMapper extends BaseMapper<BatchTest> {

    /**
     * 单sql批量插入(全量填充,高性能)
     *
     * @param batchList 待插入实体集合
     * @return int 操作成功条数
     * @author Liangyixiang
     * @date 2021/9/29
     **/
    int insertAll(@Param("list") Collection<BatchTest> batchList);
}

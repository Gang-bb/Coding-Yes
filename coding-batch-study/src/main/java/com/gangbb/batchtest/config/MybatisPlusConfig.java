package com.gangbb.batchtest.config;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.gangbb.batchtest.utils.InsertAll;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * TODO:deprecated
 *
 * @author Liangyixiang
 * @date 2021/11/26
 **/
@Configuration
public class MybatisPlusConfig {

    /**
     * sql注入器配置
     * https://baomidou.com/guide/sql-injector.html
     */
    @Bean
    public ISqlInjector sqlInjector() {
        return new DefaultSqlInjector() {
            @Override
            public List<AbstractMethod> getMethodList(Class<?> mapperClass, TableInfo tableInfo) {
                List<AbstractMethod> methodList = super.getMethodList(mapperClass, tableInfo);
                methodList.add(new InsertAll());
                return methodList;
            }
        };
    }
}
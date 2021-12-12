package com.gangbb.dozerstudy.dozer;

import org.dozer.DozerBeanMapper;

/**
 * @Author Gangbb
 * @Description 构建DozerMapper单例
 * @Date 2021/8/7
 **/
public final class DozerMapper {
    private final static DozerBeanMapper dozerMapper;

    private DozerMapper(){}

    static {
        dozerMapper = new DozerBeanMapper();
    }

    public static DozerBeanMapper get(){
        return dozerMapper;
    }
}
package com.gangbb.copybean.MapSturct.validate;

/**
 * @Author Gangbb
 * @Description 测试mapstruct校验类
 * @Date 2021/8/10
 **/
public class Validator {
    public int toId(int id) throws RuntimeException {
        if(id < 0){
            throw new RuntimeException("Invalid value in ID");
        }
        return id + 1;
    }
}
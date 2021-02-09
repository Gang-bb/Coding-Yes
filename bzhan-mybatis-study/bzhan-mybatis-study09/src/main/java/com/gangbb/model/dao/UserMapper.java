package com.gangbb.model.dao;

import com.gangbb.model.pojo.User;

import java.util.List;

/**
 * @author : Gangbb
 * @ClassName : UserMapper
 * @Description :
 * @Date : 2021/2/5 15:31
 */
public interface UserMapper {


    /**
     * 根据id查user
     *
     * @param id
     * @return
     */
    User selectUserById(int id);
}
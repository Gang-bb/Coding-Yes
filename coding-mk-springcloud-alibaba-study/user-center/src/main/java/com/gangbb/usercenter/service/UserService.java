package com.gangbb.usercenter.service;

import com.gangbb.usercenter.dao.user.UserMapper;
import com.gangbb.usercenter.domain.entity.user.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Gangbb
 * @date 2021/4/21 9:53
 * @Description:
 */
@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public User findById(Integer id){
        return userMapper.selectByPrimaryKey(id);
    }
}

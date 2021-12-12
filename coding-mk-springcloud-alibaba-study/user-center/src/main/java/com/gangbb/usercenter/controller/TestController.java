package com.gangbb.usercenter.controller;

import com.gangbb.usercenter.dao.user.UserMapper;
import com.gangbb.usercenter.domain.entity.user.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author Gangbb
 * @date 2021/4/20 21:23
 * @Description:
 */
@RestController
public class TestController {
    @Resource
    private UserMapper userMapper;

    @GetMapping("/test")
    public User testInsert(){
        User user = new User();
        user.setAvatarUrl("xxxx");
        user.setBonus(100);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        this.userMapper.insertSelective(user);

        return user;
    }
}

package com.gangbb.model.dao;

import com.gangbb.model.pojo.User;

import java.util.List;
import java.util.Map;

/**
 * @author : Gangbb
 * @ClassName : UserMapper
 * @Description :
 * @Date : 2021/2/5 15:31
 */
public interface UserMapper {

    List<User> getUserList();

    User selectUserById(int id);

    /**
     * 模糊查询
     * @param key
     * @return
     */
    List<User> selectUserByKey(String key);

    int insertUser(User user);

    int addUser(Map<String, Object> map);

    int updateUser(User user);

    int deleteUser(int id);
}

package com.gangbb.model.dao;

import com.gangbb.model.pojo.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author : Gangbb
 * @ClassName : UserMapper
 * @Description :
 * @Date : 2021/2/5 15:31
 */
public interface UserMapper {

    @Select("select * from user")
    List<User> getUsers();

}

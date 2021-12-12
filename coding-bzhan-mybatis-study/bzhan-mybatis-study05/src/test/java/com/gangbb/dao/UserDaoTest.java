package com.gangbb.dao;

import com.gangbb.model.dao.UserMapper;
import com.gangbb.model.pojo.User;
import com.gangbb.utils.MybatisUtil;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : Gangbb
 * @ClassName : UserDaoTest
 * @Description :
 * @Date : 2021/2/8 10:02
 */
public class UserDaoTest {
    /**
     * 注解获取全部用户
     */
    @Test
    public void getUser(){
        SqlSession sqlSession = null;
        try {
            //1. 获取sqlsession对象
            sqlSession = MybatisUtil.openSession();

            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<User> userList = userMapper.getUsers();


            for (User user: userList){
                System.out.println(user);
            }
        } finally {
            //3. 关闭sqlsession
            sqlSession.close();

        }
    }


}

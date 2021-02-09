package com.gangbb.dao;

import com.gangbb.model.pojo.User;
import com.gangbb.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * @author : Gangbb
 * @ClassName : UserDaoTest
 * @Description :
 * @Date : 2021/2/8 10:02
 */
public class UserDaoTest {
    /**
     * 获取用户列表语句测试
     */
    @Test
    public void TestUserMapper(){
        SqlSession sqlSession = null;
        try {
            //1. 获取sqlsession对象
            sqlSession = MybatisUtil.openSession();

            //2.方式一： getMapper
//        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
//        List<User> userList = userMapper.getUserList();

            //方式二：
            List<User> userList = sqlSession.selectList("getUserList");

            for (User user: userList){
                System.out.println(user);
            }
        } finally {
            //3. 关闭sqlsession
            sqlSession.close();

        }
    }
}

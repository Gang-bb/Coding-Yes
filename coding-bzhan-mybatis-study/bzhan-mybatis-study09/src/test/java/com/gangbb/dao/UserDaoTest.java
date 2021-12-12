package com.gangbb.dao;

import com.gangbb.model.dao.UserMapper;
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

    @Test
    public void TestUserMapper(){
        //定义第一个会话
        SqlSession sqlSession1 = null;
        //定义第二个会话
        SqlSession sqlSession2 = null;
        try {
            sqlSession1 = MybatisUtil.openSession();
            sqlSession2 = MybatisUtil.openSession();

            UserMapper userMapper1 = sqlSession1.getMapper(UserMapper.class);
            UserMapper userMapper2 = sqlSession1.getMapper(UserMapper.class);


            User user1 = userMapper1.selectUserById(23);
            System.out.println(user1);
            System.out.println("===============================");
            User user2 = userMapper2.selectUserById(23);
            System.out.println(user2);
            System.out.println(user2 == user1);


        } finally {
            //3. 关闭sqlsession
            sqlSession1.close();
            sqlSession2.close();

        }
    }
}

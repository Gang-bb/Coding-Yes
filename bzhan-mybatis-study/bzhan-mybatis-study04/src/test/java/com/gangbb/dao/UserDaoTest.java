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
     * 分页获取用户
     */
    @Test
    public void testLimit(){
        SqlSession sqlSession = null;
        try {
            //1. 获取sqlsession对象
            sqlSession = MybatisUtil.openSession();

            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            Map map = new HashMap();
            map.put("startIndex", 0);
            map.put("pageSize", 2);
            List<User> userList = userMapper.getUserListByLimit(map);


            for (User user: userList){
                System.out.println(user);
            }
        } finally {
            //3. 关闭sqlsession
            sqlSession.close();

        }
    }

    @Test
    public void testRowBounds(){
        SqlSession sqlSession = null;
        try {
            //1. 获取sqlsession对象
            sqlSession = MybatisUtil.openSession();

            //RowBounds实现
            RowBounds rowBounds = new RowBounds(0, 2);

            //通过Java代码层面实现分页
            List<User> userList = sqlSession.selectList("getUserRowBounds",null,rowBounds);


            for (User user: userList){
                System.out.println(user);
            }
        } finally {
            //3. 关闭sqlsession
            sqlSession.close();

        }
    }
}

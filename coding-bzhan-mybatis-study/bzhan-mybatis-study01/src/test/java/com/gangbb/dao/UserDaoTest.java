package com.gangbb.dao;

import com.gangbb.model.dao.UserMapper;
import com.gangbb.model.pojo.User;
import com.gangbb.utils.MybatisUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : Gangbb
 * @ClassName : UserDaoTest
 * @Description :
 * @Date : 2021/2/5 15:45
 */
public class UserDaoTest {

    /**
     * 测试连接
     * @throws IOException
     */
    @Test
    public void testEv() throws IOException {
        Reader reader =  Resources.getResourceAsReader("mybatis-config.xml");
        //构造者模式.初始化sqlSessionFactory，解析配置文件
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        System.out.println(sqlSessionFactory);
        SqlSession sqlSession = null;
        try {
            //创建SqlSession对象，SqlSession是JDBC的扩展类，用于与数据库交互
            sqlSession = sqlSessionFactory.openSession();
            //创建数据库连接(测试用)
            Connection connection = sqlSession.getConnection();
            System.out.println(connection);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(sqlSession != null){
                //如果配置文件中dataSource type="POOLED" ，则代表使用连接池，close将连接收回到连接池中
                //如果type="UNPOOLED",代表直连，close则会调用Connection.close()方法关闭连接
                sqlSession.close();
            }
        }
    }

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


    /**
     * 测试查询一条数据
     */
    @Test
    public void testSelectOne(){
        SqlSession sqlSession = null;
        try {
            //1. 获取sqlsession对象
            sqlSession = MybatisUtil.openSession();

            //2.方式一： getMapper
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.selectUserById(2);

            //方式二：
            //User user = sqlSession.selectOne("selectUserById", 1);

            System.out.println(user);
        } finally {
            //3. 关闭sqlsession
            sqlSession.close();

        }
    }

    /**
     * 测试插入一条数据
     */
    @Test
    public void insertOne(){
        SqlSession sqlSession = null;
        try {
            //1. 获取sqlsession对象
            sqlSession = MybatisUtil.openSession();

            //2.方式一： getMapper
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = new User(null, "Gangbb", "123456");
//            int i = userMapper.insertUser(user);

            //方式二：
            int i =  sqlSession.insert("insertUser", user);

            //提交事务
            sqlSession.commit(i != 0);
            System.out.println("成功插入" + i + "行");

        } finally {
            //3. 关闭sqlsession
            sqlSession.close();

        }
    }

    /**
     * 更新一个用户信息
     */
    @Test
    public void updateOne(){
        SqlSession sqlSession = null;
        try {
            //1. 获取sqlsession对象
            sqlSession = MybatisUtil.openSession();


            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //1.直接定义一个user
            User user = new User(1, "Gangbbxx", "123456");
            //或者从数据库中查出记录后在修改
            //ser user = userMapper.selectUserById(1);
            //user.setName("修改后的名字");

            int i = userMapper.updateUser(user);


            //提交事务
            sqlSession.commit(i != 0);
            System.out.println("成功更新" + i + "行");

        } finally {
            //3. 关闭sqlsession
            sqlSession.close();

        }
    }

    @Test
    public void deleteOne(){
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.openSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            int i = userMapper.deleteUser(2);

            //提交事务
            sqlSession.commit(i != 0);
            System.out.println("成功删除" + i + "行");

        } finally {
            //3. 关闭sqlsession
            sqlSession.close();

        }
    }

    /**
     * 测试用map传递参数插入一条数据
     */
    @Test
    public void addOne(){
        SqlSession sqlSession = null;
        try {
            //1. 获取sqlsession对象
            sqlSession = MybatisUtil.openSession();

            //2.方式一： getMapper
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = new User(null, "Gangbb", "123456");
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("uid", 23);
            map.put("userName", "map添加");
            map.put("password", "123456");
            int i = userMapper.addUser(map);


            //提交事务
            sqlSession.commit(i != 0);
            System.out.println("成功插入" + i + "行");

        } finally {
            //3. 关闭sqlsession
            sqlSession.close();

        }
    }

    /**
     * 模糊查询用户
     */
    @Test
    public void selectUsers(){
        SqlSession sqlSession = null;
        try {
            //1. 获取sqlsession对象
            sqlSession = MybatisUtil.openSession();

            //2.方式一： getMapper
            //UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //List<User> userList = userMapper.selectUserByKey("G");

            List<User> userList = sqlSession.selectList("selectUserByKey", "G");


            for (User user: userList){
                System.out.println(user);
            }
        } finally {
            //3. 关闭sqlsession
            sqlSession.close();

        }
    }


}

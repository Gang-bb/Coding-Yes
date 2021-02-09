package com.gangbb.utils;

/**
 * @author : Gangbb
 * @ClassName : MybatisUtil
 * @Description :
 * @Date : 2021/2/5 15:11
 */

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * MyBatisUtils工具类，创建全局唯一的 SqlSessionFactory 对象
 */
public class MybatisUtil {
    /**
     * 静态属性属于类不属于对象，且全局唯一
     */
    private static SqlSessionFactory sqlSessionFactory;

    //利用静态块再初始化类时实例化SqlSessionFactory
    static {
        try {
            //方法1： 构造者模式.初始化sqlSessionFactory，解析配置文件
            Reader reader =  Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            //方法2： 用流
//            String resource = "mybatis-config.xml";
//            InputStream inputStream = Resources.getResourceAsStream(resource);
//            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
            //抛出一个类初始化过程中的错误
            throw new ExceptionInInitializerError(e);
        }
    }

    //工具类一般都用static,通过 类名.xx() 调用

    /**
     * openSession 创建一个新的SqlSession对象
     * @return SqlSession对象
     */
    public static SqlSession openSession(){
        return sqlSessionFactory.openSession(true);
    }

    /**
     * 释放一个有效的SqlSession对象
     * @param session 即将释放的SqlSession对象
     */
    public static void closeSession(SqlSession session){
        if(session != null){
            session.close();
        }
    }
}


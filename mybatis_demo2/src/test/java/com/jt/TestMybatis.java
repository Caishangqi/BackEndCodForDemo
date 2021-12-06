package com.jt;

import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestMybatis {
    /**
     * 规则说明:
     * 1.创建SqlSessionFactory 工厂模式 作用是生产SqlSession
     * 2.获取SqlSession 理解:数据库链接+传输器对象
     * 3.获取Mapper接口对象
     * 4.完成业务调用
     * 5.关闭链接
     */
    @Test
    public void testDemo1() throws IOException {
        /*创建SqlSessionFactory*/
        //指定配置文件的根目录
        String resource = "mybatis-config.xml";
        //通过IO流 加载配置文件 org.apache.ibatis.io;包路径
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //实例化工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        /*获取SqlSession，通过SqlSession可以直接操作数据库*/
        /*从SqlSessionFactory中获取sqlSession*/
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取接口对象 - 因为没有实现类，使用的是JDK代理(代理对象)
        /*Spring 使用 JDK代理
         * Spring boot 使用的是CGLib代理
         * 特例是SpringBoot里面整合mybatis使用的是JDK代理*/
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //调用接口方法
        List<User> userList = userMapper.findAll();
        System.out.println(userList);
        //暂时手动关闭链接
        sqlSession.close();
    }

}

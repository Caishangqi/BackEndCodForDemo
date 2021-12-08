package com.jt;

import com.jt.mapper.UserAnnoMapper;
import com.jt.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@SpringBootTest
public class TestMybatisAnno {

    @Autowired
    private UserAnnoMapper userMapper;

    @Test
    /**
     * 注解
     */ public void testFindAll() {

        List<User> list = userMapper.findAll();
        System.out.println(list);
    }


    /**
     * 注解执行Sql
     *
     * @return void
     */
    @Test
    public void testFindUserById() {
        int id = 1;
        User user = userMapper.findUserById(id);
        System.out.println(user);
    }

    /**
     * 测试Mybatis一二级缓存
     * (!) 现象：如果采用SpringBoot方式测试时，发现
     * SQL语句执行了多次(一级缓存无效)
     * (!) SpringBoot整合Mybatis之后，使用Mapper
     * .find查询时，SpringBoot默认会开启多个SqlSession。
     * <p>
     * (+) 添加事物的注解
     * (!) SpringBoot中如果添加了事物注解，则默认采用
     * 同一个SqlSession
     *
     * @return void 空值
     * @author Caizii
     * @version 1.12
     * @see IOException
     */
    @Test
    @Transactional //控制事务
    public void TestCache1() {

        List<User> userList1 = userMapper.findCache1(); //第一个SqlSession
        List<User> userList2 = userMapper.findCache1(); //第二个SqlSession
        List<User> userList3 = userMapper.findCache1(); //第三个SqlSession
        List<User> userList4 = userMapper.findCache1(); //第四个SqlSession

        /*许多个SqlSession对象，操作一次就一个*/

        /*在添加事务控制注解后，就变成了一个SqlSession*/


    }

    @Test
    public void TestCache2() {

    }

}
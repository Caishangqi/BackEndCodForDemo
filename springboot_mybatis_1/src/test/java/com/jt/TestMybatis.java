package com.jt;

import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Scanner;

@SpringBootTest
public class TestMybatis {

    @Autowired
    private UserMapper userMapper; //代理对象

    @Test
    public void testDemo1() {
        List<User> userList = userMapper.findAll();
        System.out.println(userList);
    }

    @Test
    public void testUserById() {
//        Scanner scanner = new Scanner(System.in);
        int id = 1;

        User result = userMapper.findById(id);
        System.out.println(result);
    }
}
package com.jt;

import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * (!) 如果是多个参数传递，则一般采用对象的方式封装
     */

    @Test
    public void testFindByNA() {
        String name = "孙尚香";
        int age = 18;
        User user = new User();
        user.setAge(age).setName(name);
        List<User> userList = userMapper.findUserByNA(user);
        System.out.println(userList);
    }

    /**
     * (!) 如果多个参数不方便使用User对象封装时，应该使用
     * 万能的集合Map
     */
    @Test

    public void testFindByAge() {
        int minAge = 18;
        int maxAge = 100;
        Map<String, Integer> userList = new HashMap<>();
        userList.put("minAge", minAge);
        userList.put("maxAge", maxAge);
        List<User> users = userMapper.findUserByAge(userList);
        System.out.println(users);
    }

    /**
     * (!) 利用注解实现数据的封装
     */
    @Test

    public void testFindByAge2() {
        int minAge = 18;
        int maxAge = 100;

        List<User> users = userMapper.findUserByAge2(minAge, maxAge);
        System.out.println(users);
    }

    /**
     * 查询name字段中包含"君"的用户
     */
    @Test
    public void testFindUserByLike() {
        String name = ("%" + "君" + "%");
        List<User> users = userMapper.findUserByLike(name);
        System.out.println(users);
    }

    /**
     * 根据指定ID查找
     */
    @Test
    public void testFindListByIn() {
        int[] array = {1, 2, 3, 5, 7};
        List<User> users = userMapper.FindListByIn(array);
        System.out.println(users);
    }

    /**
     * 用户新增
     */
    @Test
    public void testInsertUser() {
        User user = new User();
        user.setName("张三").setAge(18).setSex("男");
        userMapper.saveUser(user);
        System.out.println("(+) Added User Completed");
    }
}
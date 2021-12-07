package com.jt;

import com.jt.mapper.DogMapper;
import com.jt.mapper.UserMapper2;
import com.jt.pojo.Dog;
import com.jt.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestMybatis2 {

    @Autowired
    private UserMapper2 userMapper; //代理对象
    @Autowired
    private DogMapper dogMapper;

    @Test
    /**
     * 根据对象中不为null的是数据，充当where条件
     */
    public void testDemo1() {
        User user = new User();
        user.setAge(18).setSex("女"); //动态变化的数据，用户可以给出其他的数据也可以不给
        List<User> userList = userMapper.findUserList(user);
        System.out.println(userList);
    }

    @Test
    /**
     * 执行动态的更新操作
     * 根据对象中不为null的元素，充当set条件。
     * where id = 固定
     */
    public void testDemo2() {
        User user = new User();
        user.setId(231).setName("冬天").setAge(18);
        userMapper.updateUser(user);
        System.out.println("(!) 更新成功！");
    }

    @Test
    /**
     * 如果name有值，则按照name查询，否则按照sex查询
     * 数据。
     */
    public void testDemo3() {
        User user = new User();
        user.setName(null).setSex("男").setAge(18);
        List<User> userList = userMapper.findUserByNS(user);
        System.out.println(userList);
    }

    @Test
    /**
     * 如果name有值，则按照name查询，否则按照sex查询
     * 数据。
     */
    public void testDemo4() {
        List<Dog> dogList = dogMapper.findAll();
        System.out.println(dogList);
    }


}
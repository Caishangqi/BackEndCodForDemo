package com.jt.controller;


import com.jt.pojo.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

//    ___         _
//   / _ \       (_)
//  / /_\ \__  __ _   ___   ___
//  |  _  |\ \/ /| | / _ \ / __|
//  | | | | >  < | || (_) |\__ \
//  \_| |_//_/\_\|_| \___/ |___/
//
//
@RestController
@RequestMapping("/axios") //前缀是axios所以要加 @RequestMapping
@CrossOrigin //解决跨域问题
public class AxiosController {

    /*业务需求说明
    需求: 根据ID查询用户信息
    URL地址: http://localhost:8080/axios/getUserById?id=100
    参数: id = 100
    返回值: User对象的JSON 伪造一个User对象
    */

    @RequestMapping("/getUserById")
    public User getUserById(Integer id) {
        //根据ID查询数据库
        User user = new User();
        user.setId(id);
        user.setName("好好学习");
        user.setAge(1000);
        user.setSex("男");
        return user;
    }

    /**
     * URL地址:  http://localhost:8080/axios/getUserByNA?id=106&name=caizii
     * 参数:         id=xxx  name=xxx
     * 返回值:     List [user1,user2]
     */
    @RequestMapping("/getUserByNA")
    public List<User> getUserByNA(User user) {
        List<User> list = new ArrayList<>();
        list.add(user);//简化赋值操作 直接返回
        list.add(user);
        return list;
    }


    /*查询name=tomcat sex=“男” 的用户 要求请求采用restFul的风格实现数据获取.
    URL地址: http://localhost:8080/axios/findUserByNS/tomcat/男
    参数: name/sex
    返回值: List*/

    /*
     * URL地址:  http://localhost:8080/axios/findUserByNS/tomcat/男
     * 参数: name/sex
     * 返回值: List<User>
     */
    @RequestMapping("/findUserByNS/{name}/{sex}") //调用set方法为属性赋值
    public List<User> findUserByNS(User user) {
        ArrayList<User> users = new ArrayList<User>();
        users.add(user);
        users.add(user);
        return users;

    }


}

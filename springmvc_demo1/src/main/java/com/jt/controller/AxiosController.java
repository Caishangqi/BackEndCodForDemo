package com.jt.controller;


import com.jt.pojo.User;
import org.springframework.web.bind.annotation.*;

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
    需求: 根据ID查询用户信息1
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


    /*
     * URL： http://localhost:8080/axios/saveUser
     * 参数：json的串
     * 返回值："新增用户成功"
     * 特殊：
     *      (!) Get是通过 ?key=value&key2=value2 获取，截取
     *          + 调用set方法
     *      (!) post请求 数据是json串，数据结构不同 所以不能使用
     *          User对象接收
     *      (!) JSON串想办法转换成User对象
     *          User对象转Json串 @ResponseBody
     *          Json串转User对象 @RequestBody
     *      (!) Json串转化要求，JSON串的属性和对象的属性必须一样，
     *          并且赋值时调用对象的set方法
     *      (!) @RequestMapping 可以支持任意类型的请求，但是这
     *          个写法不安全。要求只接受post类型的请求。
     *          @PostMapping
     *          @GetMapping
     *          @DeleteMapping
     *          @PutMapping
     *      (!) 如果是报错405，就说明请求类型不匹配
     *      (!) 如果是报错400，就说明参数类型不匹配
     *
     */

    //只能是post类型的请求才能访问这个方法
    //@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    @PostMapping("/saveUser") //这个方法只能让post方法访问
    public String saveUser(@RequestBody User user) {
        System.out.println(user);
        return "新增用户成功";
    }

    /*
     * URL: http://localhost:8080/axios/updateUser
     * 参数：JSON串
     * 返回值 String
     */
    @PutMapping("/updateUser")
    public String updateUser(@RequestBody User user) {
        System.out.println(user);
        return "修改成功";
    }

}

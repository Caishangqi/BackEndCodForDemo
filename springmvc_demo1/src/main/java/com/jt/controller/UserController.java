package com.jt.controller;

import com.jt.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;

//     ___                _                 _  _
//    / __\  ___   _ __  | |_  _ __   ___  | || |  ___  _ __
//   / /    / _ \ | '_ \ | __|| '__| / _ \ | || | / _ \| '__|
//  / /___ | (_) || | | || |_ | |   | (_) || || ||  __/| |
//  \____/  \___/ |_| |_| \__||_|    \___/ |_||_| \___||_|
//

@Controller //将类交给SpringMVC管理，SpringMVC交给spring容器管理
@ResponseBody //将数据转化为 "特殊字符串" 返回
public class UserController {

    /*URL地址 http://localhost:8080/hello get类型的请求
     * 参数：无
     * 返回值：您好，SpringMVC小甲虫の字符串*/

    @RequestMapping("/hello")
    public String hello() {
        String mes = "您好，SpringMVC小甲虫の字符串";
        return mes;

    }

    /*需求：查询后端数据 参数两个数据 name=tomcat，age=18
     * URL地址 http://localhost:8080/findUserByNA?name=tomcat&age=18
     *
     * 通过URL里面的数据动态获取key
     *
     * 需求：接收参数 name=xxx age=xxx
     * 返回值：数据正确
     * (!) 通过URL中的key获取数据。
     * (!) 如果参数众多，则可以使用对象的方式接收，要求必须有set方法
     */
    @RequestMapping("/findUserByNA")
    public String findUserByNA(String name, int age) {

        return "数据正确 " + name + ":" + age;

    }


    /*URL地址 http://localhost:8080/findUserByNA2?name=tomcat&age=18&id=100&sex=男
     * 返回值：User.toString*/
    @RequestMapping("/findUserByNA2")
    public String findUserByNA2(User user) {

        return user.toString();

    }

    @RequestMapping("/")
    public String noRes() {
        String mes = "你没访问任何东西";
        return mes;
    }

    /*
     * URL地址： http://localhost:8080/hobby?hobby=A,B,C,D
     * 参数：hobby = A,B,C,D
     * 返回值：获取参数返回即可
     * 如果遇到同名提交，则SpringMVC可以采用数组接收
     * 底层实现：截取+数组转换
     * */
    @RequestMapping("/hobby")
    public String hobby(String[] hobby) {
//        //截取
//        String[] hobbyStrings = hobby.split(",");
//        System.out.println(hobbyStrings[0]);
        //数组转换成字符串

        return Arrays.toString(hobby);
    }


}

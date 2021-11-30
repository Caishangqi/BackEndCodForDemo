package com.jt.service;
//被代理

import org.springframework.stereotype.Service;

/*代理模式
 * (+) 一般采用代理模式就是为了解耦，将公共的通用的方法
 * 功能放入代理对象种。由业务层专注于我们的业务执行即可。
 * */
@Service
public class UserServiceImpl2 implements UserService {


    @Override //必须得有这个Override不然接口没实现
//    @aba //注解主要起标记作用
    public String addUser() {
        /*业务层只专注于业务*/
        System.out.println("[+] Added User");
        String out = "这是方法输出";
        return out;
//        int a = 1/0;
    }
    /*业务层操作时要考虑数据库*/

//    @Override
//    public void addUser() {
//        try {
//            System.out.println("Start Database Server");
//            System.out.println("Added User...");
//            System.out.println("Submit data...");
//            //int a =1/0; //算数异常 运行异常
//        } catch (Exception e) {
//            System.out.println("roll Back!");
//
//        }
//
//    }

    /*如果有多个方法，操控基本相同的业务则代码重复*/

}

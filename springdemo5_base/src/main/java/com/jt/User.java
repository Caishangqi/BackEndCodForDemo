package com.jt;


import org.springframework.stereotype.Component;

@Component
public class User {
    public User() {
        System.out.println("无参构造器");
    }

    public void say() {
        System.out.println("测试是否为单例");
    }
}

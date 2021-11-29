package com.jt;

import com.jt.config.SpringConfig;
import com.jt.proxy.CGlibProxy;
import com.jt.proxy.JDKProxy;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestSpring {
    @Test
    public void TestDemo1() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        //获取目标对象
        UserService userService = context.getBean(UserService.class);
        //获取代理对象
        UserService proxy = (UserService) JDKProxy.getProxy(userService);
        //代理对象执行方法
        proxy.addUser();
        //目标对象执行方法
        userService.addUser();
    }

    @Test
    public void TestDemo2() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        //获取目标对象
        UserService userService = context.getBean(UserService.class);
        //获取代理对象
        UserService proxy = (UserService) JDKProxy.getTimeProxy(userService);
        //代理对象执行方法
        proxy.addUser();
    }

    //CGlib代理方法调用
    @Test
    public void TestDemo3() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        //获取目标对象
        UserService userService = context.getBean(UserService.class);
        //获取代理对象
        UserService proxy = (UserService) CGlibProxy.getProxy(userService);
        //代理对象执行方法
        proxy.addUser();
    }
}

package com.jt;

import com.jt.service.UserService;
import com.jt.service.config.SpringConfig;
import com.jt.service.proxy.JDKProxy;
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
}

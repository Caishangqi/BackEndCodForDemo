package com.jt;

import com.jt.config.SpringConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestSpring {

    //利用注解的方式管理对象
    @Test
    public void TestDemo1() {
        //利用注解的方式启动spring容器
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        //从容器中获取对象
        User user1 = context.getBean(User.class);
        //对象调用方法
        user1.say();
    }
}

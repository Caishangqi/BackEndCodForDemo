package com.jt;

import com.jt.config.SpringConfig;
import demo.User;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Calendar;

public class TestSpring {
    @Test
    public void TestDemo1() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        User user = context.getBean(User.class);
        user.say();
    }

    @Test
    public void TestDemo2() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        Calendar calendar = (Calendar) context.getBean("calendar");
        System.out.println("获取当前时间：" + calendar.getTime());

    }
}

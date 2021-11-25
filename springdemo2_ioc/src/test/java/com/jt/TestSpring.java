package com.jt;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {

    //Spring管理对象的测试API
    @Test
    public void testDemo1() {
        String resource = "spring.xml";
        //创建spring容器，并且加载指定的配置文件 对象已经交给了容器管理
        ClassPathXmlApplicationContext Context = new ClassPathXmlApplicationContext(resource);
        //从容器中获取对象
        User user = (User) Context.getBean("user");//通过Id名称获取对象
        User user2 = Context.getBean(User.class);//第二种方法获取对象
        user2.say();

        System.out.println(user == user2);

    }
}

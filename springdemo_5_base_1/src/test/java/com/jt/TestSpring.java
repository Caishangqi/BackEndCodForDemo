package com.jt;

import config.SpringConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestSpring {

    /*Spring默认管理的对象是单例的*/
    @Test
    public void testDemo1() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        User user = context.getBean(User.class);
        User user2 = context.getBean(User.class);

        user.say();
        user2.say(); //执行了2次构造函数所以是多例
    }

    /*测试懒加载*/
    @Test
    public void testDemo2() {
        //容器启动，对象创建
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        //从容器中获取对象(要使用)
        User user = context.getBean(User.class);
        user.say();

    }

    /*Init 测试*/
    @Test
    public void testDemo3() {
        //容器启动，对象创建
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        //从容器中获取对象(要使用)
        Person person = context.getBean(Person.class);
        person.doWork();
        /* 单例对象，容器建立，对象建立
         * 容器销毁对象也就被销毁离了*/

        //容器关闭
        context.close(); //干碎容器，@Predestroy注解的方法执行


    }
}

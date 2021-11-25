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

        //解释: Spring容器的数据结构是Map集合,Map<key,value>, key是bean中id值, value是通过反射机制实例化的对象.
        //数据结构: Map<key,value> Map<user,User对象>
        //从容器中获取对象
        User user = (User) Context.getBean("user");//通过Id名称获取对象
        User user2 = Context.getBean(User.class);//第二种方法获取对象
        user2.say();

        System.out.println(user == user2);

    }

    @Test
    public void testDemo2() throws Exception {
        User user = (User) Class.forName("com.jt.User").newInstance();
        user.say();
    }
}

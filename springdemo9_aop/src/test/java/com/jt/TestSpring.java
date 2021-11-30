package com.jt;

import com.jt.config.SpringConfig;
import com.jt.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/*Spring中的AOP 利用代理对象在不修改源代码的条件下,
  对方法进行扩展.*/

public class TestSpring {
    @Test
    public void TestDemo1() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        /*本来应该是目标对象，不过与切入点表达式的BeanId匹配，则
         * 动态生成代理对象，所以可以进行方法拓展的操作*/
        UserService userService = context.getBean(UserService.class);
        //由于是代理对象，所以方法可以拓展
        userService.addUser();
        System.out.println(userService.getClass());
    }

    @Test
    public void TestDemo2() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        /*本来应该是目标对象，不过与切入点表达式的BeanId匹配，则
         * 动态生成代理对象，所以可以进行方法拓展的操作*/
        UserService userService = (UserService) context.getBean("userServiceImpl2");
        //由于是代理对象，所以方法可以拓展
        String s = userService.addUser();
        System.out.println("测试类获取返回值" + s);
    }
}

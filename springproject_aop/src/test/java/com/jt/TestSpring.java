package com.jt;

import com.jt.config.SpringConfig;
import com.jt.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestSpring {
    @Test
    public void test01() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        //面向接口开发
        /*在AOP的内部，不能直接通过实现类创建代理对象
         * (!) 若使用JDK代理则不能直接使用实现类创建*/
        UserService bean = context.getBean(UserService.class);
        bean.addUser();
    }
}

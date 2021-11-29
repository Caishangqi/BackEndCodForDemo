package com.jt;

import com.jt.service.UserService;
import com.jt.service.config.SpringConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestSpring {
    @Test
    public void TestDemo1() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserService userService = context.getBean(UserService.class);
        userService.addUser();
    }
}

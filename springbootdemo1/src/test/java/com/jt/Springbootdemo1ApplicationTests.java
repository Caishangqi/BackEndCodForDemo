package com.jt;

import com.jt.controller.HelloController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest //测试类启动，Spring 容器启动
        /*无序等待前端信息，哈皮妹子
         *
         * (!) 测试类的包路径，必须在主启动类的同包及子包中编辑*/
class Springbootdemo1ApplicationTests {

    @Autowired
    private HelloController helloController;

    @Test
    void contextLoads() {
        System.out.println(helloController.hello());
    }

}

package com.jt.controller;

import com.jt.pojo.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PropertySource(value = "classpath:/msg.properties", encoding = "utf-8")
public class HelloController {

    @Value("${msg}")
    private String msg;

    @GetMapping("/hello")
    public String hello() {
        User user = new User();
        //连续操作数据
        user.setId(100).setName("Caizii");
        return "嘿嘿" + msg;
    }

}

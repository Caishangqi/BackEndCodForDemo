package com.jt.controller;


import com.jt.pojo.User;
import com.jt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    //@RequestMapping("/findCache")
    @GetMapping("/findCache")
    public List<User> findCache1() {
        return userService.findCache1();
    }

    @GetMapping("/findAll")
    public List<User> findAll() {
        return userService.findAll();
    }
}

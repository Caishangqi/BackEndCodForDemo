package com.jt.controller;

import com.jt.pojo.User;
import com.jt.service.UserService;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin //前后端跨域操作
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/findAll")
    public List<User> findAll() {

        return userService.findAll();
    }

    /*
     * 完成用户登录操作
     * url: user/login
     * 参数: username/password json串
     * 返回值: SysResult对象，包括status msg data(密钥)
     * */
    @PostMapping("/login")
    public SysResult login(@RequestBody User user) {
        //根据用户名和密码查询数据库 true: 密钥 false: 空
        String token = userService.login(user);
        if (token == null) {
            //表示后端查询失败 返回用户 201
            return SysResult.fail();
        } //表示有数据，返回值为 200
        return SysResult.success(token);
    }

}

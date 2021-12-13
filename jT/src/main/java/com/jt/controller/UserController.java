package com.jt.controller;

import com.jt.pojo.User;
import com.jt.service.UserService;
import com.jt.vo.PageResult;
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

    /**
     * 将用户的列表进行分页查询
     * url：/user/list
     * 参数：query=查询关键字&pageNum=1&pageSize=10
     * 请求类型：get
     * 返回值：SYsResult(pageResult)
     */
    @GetMapping("/list")
    public SysResult findUserList(PageResult pageResult) { //参数3个
        pageResult = userService.findUserList(pageResult); //参数+2
        return SysResult.success(pageResult); //返回5个参数的pageResult
    }

    /**
     * 状态修改
     * url：/user/status/{id}/{status}
     * 参数： id/status
     * 返回值：SysResult对象
     */
    @PutMapping("/status/{id}/{status}")
    public SysResult updateStatus(User user) {
        //@PathVariable Integer id, @PathVariable Boolean status
        //==> 在这里要插入AOP方法拓展 报错 (全局异常处理机制的实现)
        userService.updateStatus(user);
        return SysResult.success();
    }

    /**
     * 根据ID删除用户
     * url： /user/{id}
     * 类型：delete
     * 参数：id
     * 返回值：null
     */
    @DeleteMapping("/{id}")
    public SysResult deleteUserById(@PathVariable("id") Integer id) {
        //deleteUserById(User user){
        userService.deleteUserById(id);
        return SysResult.success();
    }

    /**
     * 新增用户
     * url： /user/addUser
     * 请求类型 POST
     * 请求参数: 整个form表单数据封装为js对象进行参数传递
     */
    @PostMapping("/addUser")
    public SysResult addUser(@RequestBody User user) {
        userService.addUser(user);
        return SysResult.success();
    }

    /**
     * 根据ID查询用户信息
     * 请求路径: /user/{id}
     * 请求类型: GET
     * 返回值: SysResult对象 data中要有User对象
     */
    @GetMapping("/{id}")
    public SysResult findUserById(@PathVariable Integer id) {
        User user = userService.findUserById(id);
        return SysResult.success(user);
    }

    /**
     * 根据用户ID更新数据
     * 请求路径: /user/updateUser
     * 请求类型: PUT
     * 请求参数: User对象结构
     * 返回值: SysResult对象 data: null
     */
    @PutMapping("/updateUser")
    public SysResult updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return SysResult.success();
    }


}

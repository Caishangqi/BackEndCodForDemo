package com.jt.service;

import com.jt.annotation.permission;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    @permission(name = "update")
    public void addUser() {
        System.out.println("[+] 添加用户信息");
    }
}

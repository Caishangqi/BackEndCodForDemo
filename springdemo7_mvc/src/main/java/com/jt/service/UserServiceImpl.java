package com.jt.service;

import com.jt.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper; //默认按照类型注入


    @Override
    public void addUser() {
        userMapper.addUser();
    }


}

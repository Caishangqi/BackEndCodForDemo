package com.jt.service;

import com.jt.mapper.UserAnnoMapper;
import com.jt.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserAnnoMapper userMapper;

    @Override
    public List<User> findCache1() {

        return userMapper.findCache1();
    }

}

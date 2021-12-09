package com.jt.service;

import com.jt.pojo.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    String login(User user);
}

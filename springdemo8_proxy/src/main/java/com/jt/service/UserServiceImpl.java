package com.jt.service;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public void addUser() {
        System.out.println("Added User...");
    }
}

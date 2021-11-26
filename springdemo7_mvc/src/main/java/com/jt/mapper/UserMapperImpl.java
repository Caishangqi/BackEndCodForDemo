package com.jt.mapper;

import org.springframework.stereotype.Repository;

@Repository //为了让开发者更有层级的概念
public class UserMapperImpl implements UserMapper {
    @Override
    public void addUser() {
        System.out.println("add new member to database...");

    }
}

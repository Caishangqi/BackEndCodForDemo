package com.jt.mapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

@Repository
@PropertySource(value = "classpath:/user.properties", encoding = "utf-8")
public class UserMapperImpl implements UserMapper {
    @Value("${user.username}")
    private String username;

    @Override
    public void addUser() {
        System.out.println("(+) added User to Database 1/1 " + username);
    }
}

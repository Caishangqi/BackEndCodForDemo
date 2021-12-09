package com.jt.mapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

@Repository //为了让开发者更有层级的概念
//  classpath:/ 代表resource的根目录
@PropertySource(value = "classpath:/user.properties", encoding = "utf-8")
//如果注解中只有一个属性则可以省略 value =
public class UserMapperImpl implements UserMapper {
//    @Value("Caizii") //<--其实还是写死了
    /* @Value 注解赋值
     * 只能为 基本类型 赋值
     */

    //让spring容器把值赋予过来
    //表达式：固定写法 springel的表达式 取值方式 缩写 spel 表达式
    //规则：通过key 动态获取 spring容器的value
    @Value("${user.username}")
    private String username;

    @Override
    public void addUser() {
        System.out.println("add new member to database..." + username);

    }

}

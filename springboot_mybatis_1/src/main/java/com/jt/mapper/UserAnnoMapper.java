package com.jt.mapper;

import com.jt.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/*
    该Mapper主要测试注解开发
 */
@Mapper
public interface UserAnnoMapper {
    //利用注解可以根据返回值类型。自动映射
    /*规则：
     *   (!) 注解和映射文件二选一 映射文件为主导
     *   (!) 注解的写法一般适用于简单的条做，关联查询不适用*/
    @Select("select * from demo_user")
    List<User> findAll(); //垃圾

    @Select("select * from demo_user where id = #{id}")
    User findUserById(int id);

    List<User> findCache1();
}

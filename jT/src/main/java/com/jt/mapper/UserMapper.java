package com.jt.mapper;

import com.jt.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> findAll();

    User findUserByUP(User user);

    @Select("select count(1) from user")
    long findTotal();

    //只支持单值传参 封装为map集合
    List<User> findUserList(@Param("start") int start, @Param("size") int size, @Param("query") String query);

    @Update("update user set status = #{status},updated = #{updated} where id = #{id}")
    void updateStatus(User user);

    @Delete("delete from user where id = #{id}")
    void deleteUserById(Integer id);

    void addUser(User user);

    @Select("select * from user where id = #{id}")
    User findUserById(Integer id);

    void updateUser(User user);
}

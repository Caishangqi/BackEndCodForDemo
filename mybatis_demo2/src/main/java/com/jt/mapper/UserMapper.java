
package com.jt.mapper;


import com.jt.pojo.User;

import java.util.List;

public interface UserMapper {

    //指定接口的方法 查询demo_user的全部数据
    List<User> findAll();

}

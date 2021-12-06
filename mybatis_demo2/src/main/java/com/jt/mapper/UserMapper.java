
package com.jt.mapper;


import com.jt.pojo.User;

import java.util.List;

public interface UserMapper {

    //指定接口的方法 查询demo_user的全部数据
    List<User> findAll(); //查找表返回多个值

//    User findAll2(); //查找表返回一个值
//
//    int update(); //返回操作是否成功的0或者1
//
//    void update2();

}


package com.jt.mapper;


import com.jt.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
/*把这个对接口交给Spring容器管理 Map<userMapper,代理对象(JDK)>*/
public interface UserMapper2 {


    List<User> findUserList(User user);

    void updateUser(User user);

    List<User> findUserByNS(User user);
}

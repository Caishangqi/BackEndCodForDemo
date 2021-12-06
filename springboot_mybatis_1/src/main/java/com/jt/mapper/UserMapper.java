
package com.jt.mapper;


import com.jt.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
/*把这个对接口交给Spring容器管理 Map<userMapper,代理对象(JDK)>*/
public interface UserMapper {

    void saveUser(User user);

    //指定接口的方法 查询demo_user的全部数据
    List<User> findAll(); //查找表返回多个值

    User findById(int id); //根据ID查询数据库

    List<User> findUserByNA(User user);

    List<User> findUserByAge(Map<String, Integer> userList);

    /*
     * Mybatis只支持单值传参 将多值封装为单值
     * 注解：@Param("key") 为key int minAge 为值
     * 将数据封装为Map集合
     * */
    List<User> findUserByAge2(@Param("minAge") int minAge, @Param("maxAge") int maxAge);

    List<User> findUserByLike(String name);

    List<User> FindListByIn(int[] array);

    //    User findAll2(); //查找表返回一个值
    //
    //    int update(); //返回操作是否成功的0或者1
    //
    //    void update2();

}

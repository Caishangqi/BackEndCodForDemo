
package com.jt.mapper;


import com.jt.pojo.Dog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
/*把这个对接口交给Spring容器管理 Map<userMapper,代理对象(JDK)>*/
public interface DogMapper {

    List<Dog> findAll();

}

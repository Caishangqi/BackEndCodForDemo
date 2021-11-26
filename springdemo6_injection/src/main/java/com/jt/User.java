package com.jt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class User {

    //将当前接口的实现类自动注入
    @Autowired
    @Qualifier("dog") //该注解不能单独使用，必须配合@Autowired使用
    /*根据 key进行指定注入*/

    @Resource(name = "dog") //@Autowired + @Qualifier("dog")
    /*如果需要依赖注入，则对象必须交给Spring容器
     * 管理*/
    private Pet pet; //Pet有两个实现类，你不能让程序自己选，要指定。

    public void say() {
        //调用宠物方法
        pet.hello();
    }


}

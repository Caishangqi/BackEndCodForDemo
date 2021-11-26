package com.jt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class User {

    //将当前接口的实现类自动注入
    @Autowired

    /*如果需要依赖注入，则对象必须交给Spring容器
     * 管理*/
    private Pet pet;

    public void say() {
        //调用宠物方法
        pet.hello();
    }


}

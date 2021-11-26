package com.jt;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/*使用注解来分析Spring管理的对象的生命周期*/

@Component //将对象交给Spring容器管理 key=person value=反射对象
public class Person {


    public Person() {
        System.out.println("Init 0 default...");
    }

    @PostConstruct //再对象创建之后立即调用
    public void init() {
        System.out.println("Init 10...");
    }

    //业务方法
    public void doWork() {
        System.out.println("Working...");
    }

    @PreDestroy //对象销毁前调用
    public void doVoid() {
        System.out.println("Destroy...");
    }


}

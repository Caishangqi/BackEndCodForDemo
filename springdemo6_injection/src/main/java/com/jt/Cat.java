package com.jt;

import org.springframework.stereotype.Component;

@Component //将对象交给spring容器。 key是cat，value是映射的
public class Cat implements Pet {


    @Override
    public void hello() {
        System.out.println("meo");
    }
}

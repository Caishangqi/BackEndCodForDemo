package com.jt;

import org.springframework.stereotype.Component;

@Component //将类交给Spring容器管理
public class Dog implements Pet {
    @Override
    public void hello() {
        System.out.println("Bark");
    }
}

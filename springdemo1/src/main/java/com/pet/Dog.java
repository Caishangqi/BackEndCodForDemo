package com.pet;

public class Dog implements Pet {
    @Override
    public void hello() {
        System.out.println("有狗叫？");
    }
}

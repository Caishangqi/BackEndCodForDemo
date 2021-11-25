package com.pet;

public class main {
    /**
     * 说明： 如果用户喜欢狗
     * 讨论： 这样的代码有什么问题
     * 问题描述:
     * 1. 宠物对象与当前的用户紧紧的绑定在一起. 耦合性高
     * 2. 对象与方法 紧紧的绑定在一起. 耦合性高
     */
    //private static Dog dog = new Dog();
    //private static Cat cat = new Cat();
    //面向接口开发: 优势解决了属性与方法的耦合性问题

    private static Pet pet = new Dog();

    public static void main(String[] args) {

        pet.hello();

    }
}

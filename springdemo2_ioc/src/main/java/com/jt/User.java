package com.jt;


public class User {

    //反射方法创建对象时，必然调用对象的无参构造!
    public User() {
        System.out.println("User的无参构造");
    }

    public void say() {
        System.out.println("Is user");
    }
}

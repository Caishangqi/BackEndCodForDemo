package com.jt.pojo;

//     ___    ___    __     ___
//    / _ \  /___\   \ \   /___\
//   / /_)/ //  //    \ \ //  //
//  / ___/ / \_//  /\_/ // \_//
//  \/     \___/   \___/ \___/
//


/*POJO（Plain Ordinary Java Object）简单的Java对象，实际就是普通JavaBeans，是为了避免和EJB混淆所创造的简称。
使用POJO名称是为了避免和EJB混淆起来, 而且简称比较直接. 其中有一些属性及其getter setter方法的类,没有业务逻辑，有时
可以作为VO(value -object)或dto(Data Transform Object)来使用.当然,如果你有一个简单的运算属性也是可以的,但不允
许有业务方法,也不能携带有connection之类的方法。*/



/*POJO规范
 * (!) 属性类型必须为包装类型
 * (!) 必须添加Get/Set方法
 * (!) POJO类型必须实现序列化接口*/

import java.io.Serializable;

//pojo还必须实现序列化接口
public class User implements Serializable {

    //所有对象的属性应该用包装类型，因为包装类型默认值为空而不是0
    private Integer id;
    private String name;
    private Integer age;
    private String sex;

    //必须添加set/get方法
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }
}

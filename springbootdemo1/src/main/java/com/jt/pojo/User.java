package com.jt.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/*以下是pojo使用的4个注解*/
@Data
@Accessors(chain = true) //重写set方法，实现链式加载
@NoArgsConstructor //无参构造方法
@AllArgsConstructor //全参构造方法
public class User implements Serializable {

    private Integer id;
    private String name;

    public User setId(Integer id) {
        this.id = id;
        return this; //当前对象 运行期有效

    }

    public User setName(String name) {
        this.name = name;
        return this; //当前对象 运行期有效
    }


}

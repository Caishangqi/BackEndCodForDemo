package com.jt.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@NoArgsConstructor //需要无参构造
@AllArgsConstructor //需要全参构造
public class Emp implements Serializable {

    private Integer id;
    private Integer age;
    private String name;
    /*关联关系： 一个员工对应一个部门*/
    private Dept dept;
}

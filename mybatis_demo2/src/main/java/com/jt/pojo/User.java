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
/*
 * 储存信息
 */
public class User implements Serializable {
    private Integer id;
    private Integer age;
    private String name;
    private String sex;
}

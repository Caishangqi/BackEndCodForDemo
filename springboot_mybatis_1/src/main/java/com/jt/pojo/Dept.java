package com.jt.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
@NoArgsConstructor //需要无参构造
@AllArgsConstructor //需要全参构造
public class Dept implements Serializable {
    private String deptName;
    private Integer deptId;
    //一个部门下对应多个员工
    private List<Emp> emps;

}

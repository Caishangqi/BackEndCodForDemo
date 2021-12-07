package com.jt;

import com.jt.mapper.DeptMapper;
import com.jt.mapper.EmpMapper;
import com.jt.pojo.Dept;
import com.jt.pojo.Emp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestMybatis3 {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private DeptMapper deptMapper;

    @Test
    /**
     * 一对一
     */
    public void testOneToOne() {
        List<Emp> empList = empMapper.findAll();
        System.out.println(empList);
    }

    @Test
    /**
     * 一对多
     * 一个部门中要求获取多个员工
     */
    public void testMore() {
        List<Dept> list = deptMapper.findAll();
        System.out.println(list);
    }


}
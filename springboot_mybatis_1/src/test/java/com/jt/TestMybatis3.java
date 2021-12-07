package com.jt;

import com.jt.mapper.EmpMapper;
import com.jt.pojo.Emp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestMybatis3 {

    @Autowired
    private EmpMapper empMapper;

    @Test
    /**
     * 一对一
     */
    public void testOneToOne() {
        List<Emp> empList = empMapper.findAll();
        System.out.println(empList);
    }


}
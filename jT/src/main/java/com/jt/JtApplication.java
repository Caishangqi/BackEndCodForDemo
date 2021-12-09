package com.jt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jt.mapper") //将该路径下的mapper接口交给容器管理
public class JtApplication {

    public static void main(String[] args) {
        SpringApplication.run(JtApplication.class, args);
    }

}

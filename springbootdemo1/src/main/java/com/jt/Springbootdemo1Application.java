package com.jt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Springbootdemo1Application {

    /**
     * 1.   只启动类执行时,开始加载@SpringBootApplication注解
     * <p>
     * 2.   说明: @ComponentScan(“包路径!!!”)
     * 规则: 当前包扫描的路径 默认就是主启动类所在的包路径…
     * 注意事项: 以后写代码 必须在主启动类所在包路径的 同包及子包中编辑
     * <p>
     * 3.   注解名称: @EnableAutoConfiguration 启动自动化的配置
     * 规则: 该注解的作用用来加载springBoot-start-xxx的启动
     * 项.当主启动类执行时,则会开始加载启动项中的配置.则功能加载
     * 成功.
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(Springbootdemo1Application.class, args);
    }

}

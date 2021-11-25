package com.jt.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("factory")
@ComponentScan("demo")

//当spring容器通过时，指定扫描的包路径，可以扫描他的子孙包
//用在配置类中！
public class SpringConfig {

}

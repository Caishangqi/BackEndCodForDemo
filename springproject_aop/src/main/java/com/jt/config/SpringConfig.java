package com.jt.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Configuration
@Component
@EnableAspectJAutoProxy //使用spring的机制 ，springboot默认开启
@ComponentScan("com.jt")
public class SpringConfig {
}

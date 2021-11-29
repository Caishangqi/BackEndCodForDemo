package com.jt.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.jt")
@EnableAspectJAutoProxy //启用切面自动注解 - 开启AOP
public class SpringConfig {
}

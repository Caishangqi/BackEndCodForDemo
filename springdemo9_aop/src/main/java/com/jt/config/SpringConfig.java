package com.jt.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
//                        __  _
//    ___   ___   _ __   / _|(_)  __ _
//   / __| / _ \ | '_ \ | |_ | | / _` |
//  | (__ | (_) || | | ||  _|| || (_| |
//   \___| \___/ |_| |_||_|  |_| \__, |
//                               |___/
@Configuration
@ComponentScan("com.jt")
@EnableAspectJAutoProxy(proxyTargetClass = true) //启用切面自动注解 - 开启AOP
/* Spring 中 proxyTargetClass 默认是false，时使用JDK代理
 * 如果设置成true则使用CGlib代理。
 *
 * (SpringBoot)如果被代理者没有接口则自动使用CGlib
 * 因为默认是面向接口。Boot默认CGlib
 * */
public class SpringConfig {
}

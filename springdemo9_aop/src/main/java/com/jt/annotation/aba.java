package com.jt.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) //运行期有效
@Target(ElementType.METHOD) //对方法有效
public @interface aba {
}

package com.jt.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component //交给spring容器管理
@Aspect //标识这是一个切面类
@Order(1) //按照顺序第一个执行，数字越小越靠前

/*顺序可以决定嵌套结构，B先开始，然后
 * B里面嵌着A*/

public class SpringAOP2 {

    //通过环绕通知，指定切入点表达式
    @Around("@annotation(com.jt.annotation.aba)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //如果有下一个通知，则执行通知方法，没有的话执行目标方法
        System.out.println("[I] 执行环绕通知B 开始");
        Object result = proceedingJoinPoint.proceed();
        System.out.println("[I] 执行环绕通知B 结束");
        return result;
    }

}

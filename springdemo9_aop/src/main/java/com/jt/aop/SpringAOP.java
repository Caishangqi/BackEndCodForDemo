package com.jt.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component //讲该类交给Spring容器管理
@Aspect//标识该类是一个切面
public class SpringAOP {

    @Pointcut("bean(userServiceImpl)") //切入点表达式
    public void pointCut() {

    }

    //前置通知：在目标方法执行之前执行 (在目标方法执行前执行)
    //只要满足切面表达式则创建代理对象
    @Before("pointCut()")
    public void before() {
        System.out.println("[!] This is a Before");
    }

    //后置通知：在目标方法执行之后执行
    @AfterReturning("pointCut()")
    public void afterReturn() {
        System.out.println("[!] This is an AfterReturning");
    }

    //异常通知：目标方法执行报错时，执行该通知
    @AfterThrowing("pointCut()")
    public void afterThrowing() {
        System.out.println("[x] This ia an AfterThrowing");
    }

    //最终通知：目标方法之后都要执行的通知
    @After("pointCut()")
    public void after() {
        System.out.println("[!] This is an After, must use");
    }

    //(!) 环绕通知：在目标方法执行前后都要执行(控制目标方法)
    /*ProceedingJoinPoint 处理的加入点*/
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("(!) 目标方法执行前(环绕)");
        //底层调用动态代理的invoke方法，就是执行目标方法
        Object result = joinPoint.proceed();
        System.out.println("(!) 目标方法执行后(环绕)");
        return result;
    }


}

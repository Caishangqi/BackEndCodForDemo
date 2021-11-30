package com.jt.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component //讲该类交给Spring容器管理
@Aspect//标识该类是一个切面
public class SpringAOP {


//                                            _
//                                           (_)
//    ___ __  __ _ __   _ __   ___  ___  ___  _   ___   _ __
//   / _ \\ \/ /| '_ \ | '__| / _ \/ __|/ __|| | / _ \ | '_ \
//  |  __/ >  < | |_) || |   |  __/\__ \\__ \| || (_) || | | |
//   \___|/_/\_\| .__/ |_|    \___||___/|___/|_| \___/ |_| |_|
//              | |
//              |_|


    /* 注解表达式一般execution和annotation这两种
     * 比较常用*/

    //execution 表达式
    /* 作用：粒度比较细，可以按照方法参数进行匹配
     * execution(返回值类型 包名.类名.方法名(参数列表))
     * 在这里参数的通配符是 .. 不是 ...*/
    @Pointcut("execution(* com.jt.service.UserServiceImpl2.addUser())") // *代表任意类型返回值
    public void pointCut2() {

    }

    /* annotation表达式 (拦截注解)
     * (!) 根据用户的自定义注解进行拦截
     * (1) 先创建了一个自定义注解 aba 在 annotation 包下
     * (!) 格式：@annotation(注解类型)*/
    @Pointcut("@annotation(com.jt.annotation.aba)")
    public void pointCut3() {
    }


    //@Pointcut("bean(userServiceImpl)") //切入点表达式写的是bean的ID
    @Pointcut("within(com.jt.service..*)")
    public void pointCut() {

    }


//                                                          __
//                                                         /  |
//    __ _  _ __   _ __    ___   _   _  _ __    ___   ___  `| |
//   / _` || '_ \ | '_ \  / _ \ | | | || '_ \  / __| / _ \  | |
//  | (_| || | | || | | || (_) || |_| || | | || (__ |  __/ _| |_
//   \__,_||_| |_||_| |_| \___/  \__,_||_| |_| \___| \___| \___/
//
//

    /*可以记录程序执行的各个过程 为日志提供记录*/


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


//                                                          _____
//                                                         / __  \
//    __ _  _ __   _ __    ___   _   _  _ __    ___   ___  `' / /'
//   / _` || '_ \ | '_ \  / _ \ | | | || '_ \  / __| / _ \   / /
//  | (_| || | | || | | || (_) || |_| || | | || (__ |  __/ ./ /___
//   \__,_||_| |_||_| |_| \___/  \__,_||_| |_| \___| \___| \_____/
//
//

    /*第二类环绕通知 可以控制目标方法是否执行，环绕通知可以
     * 控制也位于流转的过程
     * (!) 权限的校验
     * (!) 缓存系统
     * (!) 异常 处理 等*/

    //(!) 环绕通知：在目标方法执行前后都要执行(控制目标方法是否执行)
    /*ProceedingJoinPoint 处理的加入点*/
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("(!) 目标方法执行前(环绕)");
        //底层调用动态代理的invoke方法，就是执行目标方法
        Object result = joinPoint.proceed();
        System.out.println("(!) 目标方法执行后(环绕)");
        return result;
    }

//                                                           ___  ______  _____
//                                                          / _ \ | ___ \|_   _|
//    __ _  _ __   _ __    ___   _   _  _ __    ___   ___  / /_\ \| |_/ /  | |
//   / _` || '_ \ | '_ \  / _ \ | | | || '_ \  / __| / _ \ |  _  ||  __/   | |
//  | (_| || | | || | | || (_) || |_| || | | || (__ |  __/ | | | || |     _| |_
//   \__,_||_| |_||_| |_| \___/  \__,_||_| |_| \___| \___| \_| |_/\_|     \___/
//
//

    /*需求
     * (!) 获取当前的目标对象的类型
     * (!) 获取当前的方法名称
     * (!) 获取当前传递的参数*/
    @Before("pointCut2()")
    public void before2(JoinPoint joinpoint) { //连接点：获取方法中的数据

        //获取目标对象的类型
        Class<?> aClass = joinpoint.getTarget().getClass();
        //获取方法名称
        String methodName = joinpoint.getSignature().getName();
        Class declaringType = joinpoint.getSignature().getDeclaringType();
        Object[] args = joinpoint.getArgs();
        System.out.println("[I] 一个前置通知");
        System.out.println("[I] 类型 " + aClass);
        System.out.println("[I] 方法名称 " + methodName);
        System.out.println("[I] 类名称 " + declaringType);
        System.out.println("[I] 方法中携带的参数 " + Arrays.toString(args));
    }

    /*需求
     * (!) 记录目标方法返回值
     * 通过returning = "result"属性，获取目标方法的返回值
     ，当作参数传给result。
     *
     * (!) 通过属性值 来获取方法返回值
     */

    //后置通知：在目标方法执行之后执行
    @AfterReturning(value = "pointCut2()", returning = "result")
    public void afterReturn2(Object result) {
        System.out.println("[!] 这是一个后置通知 " + result);
    }


}

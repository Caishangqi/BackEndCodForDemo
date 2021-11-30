package com.jt.aop;

import com.jt.annotation.permission;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Aspect
@Component
public class SpringAOP {
    //ArrayList是线程不安全的 (并发操作不用)
    private List<String> list = new ArrayList<String>(); //单机版本
    //private List<String> list = new Vector<String>();
    /*vector内置线程锁 synchronized*/

    @PostConstruct
    public void init() {
        list.add("add"); //add 操作
        list.add("update"); //update 操作
        list.add("modify");
        list.add("delete");
    }


    /**
     * Around object.
     *
     * @param proceedingJoinPoint the proceeding join point
     * @param perm                the perm
     * @return the object
     * @throws Throwable the throwable
     */
//    @Around("execution(* com.jt.service..*.*(..))")
    @Around("@annotation(perm)") //只是拦截注解，获取注解的数据
    //如果有多个参数，JoinPoint必须写在第一位
    /**
     * @Description: <h2></h2>
     * @param proceedingJoinPoint
     * @param perm
     * @return: {@link Object}
     * @throws
     * @author: Caizii
     * @Date: 2021/11/30
     */
    public Object around(ProceedingJoinPoint proceedingJoinPoint, permission perm) throws Throwable {

        String name = perm.name();
        //判断list集合中是否有权限
        if (list.contains(name)) {
            System.out.println("[!] 环绕通知开始，权限等级" + name);
            long lstart = System.currentTimeMillis();
            Object result = proceedingJoinPoint.proceed();
            long lend = System.currentTimeMillis();

            Class<?> aClass = proceedingJoinPoint.getTarget().getClass();
            Class className = proceedingJoinPoint.getSignature().getDeclaringType();
            String methodName = proceedingJoinPoint.getSignature().getName();
            Object[] args = proceedingJoinPoint.getArgs();

            System.out.println("类型 " + aClass + " 类的名称 " + className + " 方法名称 " + methodName + " 参数列表 " + Arrays.toString(args));
            System.out.println("[!] 环绕通知结束,耗时" + (lend - lstart));

            return result;
        } else {
            System.out.println("[x] 您没有权限");
        }
        return null;

    }
}

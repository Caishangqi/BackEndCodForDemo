package com.jt.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CGlibProxy {

    public static Object getProxy(Object target) {
        //创建增强器对象 enhancer
        Enhancer enhancer = new Enhancer();
        //设定父级 目标对象
        enhancer.setSuperclass(target.getClass());
        //定义回调的方法 - 代理对象执行目标方法时调用
        enhancer.setCallback(getMethodInterceptor(target));
        //也可以通过接口调用
        Class<?>[] interfaces = target.getClass().getInterfaces();
        //enhancer也可以使用接口进行调用，有没有无所谓，括号内也可以时null
        enhancer.setInterfaces(interfaces);
        //创建代理对象
        return enhancer.create();

    }

    public static MethodInterceptor getMethodInterceptor(Object target) {
        return new MethodInterceptor() {

            @Override
            public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                System.out.println("事物开始");
                //执行目标方法
                Object result = method.invoke(target, args);
                System.out.println("事物提交");
                return result;
            }
        };
    }

}

package com.jt.service.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxy {

    //传入target目标对象-获取代理对象
    //利用代理对象实现方法的拓展
    public static Object getProxy(Object target) {
        //获取类加载器 (先得到类型) (再得到类加载器)
        ClassLoader classLoader = target.getClass().getClassLoader();
        //获取接口的数组类型
        Class<?>[] interfaces = target.getClass().getInterfaces();
        //代理对象执行方法时的回调方法(代理对象调用方法时执行的InvocationHandler)
        return Proxy.newProxyInstance(classLoader, interfaces, invocationHandler(target));
    }

    //invocationHandler  “调用处理器”

    //要求必须传递目标对象
    public static InvocationHandler invocationHandler(Object target) {
        return new InvocationHandler() {
            /*
             * 参数说明：
             * proxy 代理对象
             * method*/
            @Override
            //这里的Object时目标方法的返回值
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //拓展方法
                System.out.println("事物开始");
                //执行目标方法 - 调用
                //这里result是调用目标方法的返回值
                Object result = method.invoke(target, args);
                //拓展方法
                System.out.println("事物提交");
                return result;
            }
        };
    }

}

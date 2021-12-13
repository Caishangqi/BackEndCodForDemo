package com.jt.AOP;

import com.jt.vo.SysResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理机制：只拦截Controller层
 * 底层的东西会慢慢往上抛，只用拦截Controller
 */

@RestControllerAdvice //advice 通知
public class SystemException {

    //指定异常的类型进行拦截
    @ExceptionHandler(RuntimeException.class) //只要出现运行时异常就进行拦截
    public SysResult exception(Exception e) {
        e.printStackTrace(); //控制台要打出异常昕兮
        return SysResult.fail(); //201

    }

}

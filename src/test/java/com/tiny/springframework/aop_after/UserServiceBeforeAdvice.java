package com.tiny.springframework.aop_after;

import com.tiny.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @Descrpition
 * @Date 2025/3/13
 */
public class UserServiceBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("拦截方法：" + method.getName());
    }
}

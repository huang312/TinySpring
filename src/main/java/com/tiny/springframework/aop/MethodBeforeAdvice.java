package com.tiny.springframework.aop;

import java.lang.reflect.Method;

/**
 * @Descrpition
 * @Date 2025/3/10
 */
public interface MethodBeforeAdvice extends BeforeAdvice{
    void before(Method method, Object[] args, Object target) throws Throwable;
}

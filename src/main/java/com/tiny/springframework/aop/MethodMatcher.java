package com.tiny.springframework.aop;

import java.lang.reflect.Method;

/**
 * @Descrpition
 * @Date 2024/10/5
 */
public interface MethodMatcher {
    /**
     * Perform static checking whether the given method matches.
     * @param method
     * @param targetClass
     * @return  whether or not this method matchs statically
     */
    boolean matches(Method method, Class<?> targetClass);
}

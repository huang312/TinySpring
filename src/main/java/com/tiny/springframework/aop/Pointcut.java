package com.tiny.springframework.aop;

/**
 * @Descrpition
 * @Date 2024/10/5
 */
public interface Pointcut {
    /**
     * Return the ClassFilter for this pointcut.
     * @return
     */
    ClassFilter getClassFilter();

    /**
     * Return the MethodMatcher for this pointcut.
     * @return
     */
    MethodMatcher getMethodMatcher();
}

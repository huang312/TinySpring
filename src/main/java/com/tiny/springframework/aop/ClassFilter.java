package com.tiny.springframework.aop;

/**
 * @Descrpition
 * @Date 2024/10/5
 */
public interface ClassFilter {
    /**
     * should the pointcut apply to the given interface or target class?
     *
     * @param clazz the candidate target class
     * @return  whether the advice should apply to the given target class
     */
    boolean matches(Class<?> clazz);
}

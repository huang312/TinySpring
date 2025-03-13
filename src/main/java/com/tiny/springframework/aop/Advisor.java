package com.tiny.springframework.aop;

import org.aopalliance.aop.Advice;

/**
 * @Descrpition
 * @Date 2025/3/10
 */
public interface Advisor {
    Advice getAdvice();
}

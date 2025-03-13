package com.tiny.springframework.aop;

/**
 * @Descrpition
 * @Date 2025/3/10
 */
public interface PointCutAdvisor extends Advisor{
    Pointcut getPointcut();
}

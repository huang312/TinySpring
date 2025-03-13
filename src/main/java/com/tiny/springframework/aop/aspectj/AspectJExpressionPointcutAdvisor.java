package com.tiny.springframework.aop.aspectj;

import com.tiny.springframework.aop.PointCutAdvisor;
import com.tiny.springframework.aop.Pointcut;
import org.aopalliance.aop.Advice;

/**
 * @Descrpition
 * @Date 2025/3/10
 */
public class AspectJExpressionPointcutAdvisor implements PointCutAdvisor {
    private AspectJExpressionPointcut pointcut;
    private Advice advice;
    private String expression;

    public void setExpression(String expression) {
        this.expression = expression;
    }
    @Override
    public Advice getAdvice() {
        return advice;
    }

    @Override
    public Pointcut getPointcut() {
        if (null == pointcut) {
            pointcut = new AspectJExpressionPointcut(expression);
        }
        return pointcut;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }
}

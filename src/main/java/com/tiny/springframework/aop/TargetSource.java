package com.tiny.springframework.aop;

/**
 * @Descrpition
 * @Date 2024/10/5
 */
public class TargetSource {
    private final Object target;

    public TargetSource(Object target) {
        this.target = target;
    }

    public Class<?>[] getTargetClass(){
        return this.target.getClass().getInterfaces();
    }
    public Object getTarget() {
        return target;
    }
}

package com.tiny.springframework.bean.factory;

/**
 * @Descrpition 实现此接口，即能感知到所属的BeanName
 * @Date 2024/9/4
 */
public interface BeanNameAware extends Aware{
    void setBeanName(String beanName);
}

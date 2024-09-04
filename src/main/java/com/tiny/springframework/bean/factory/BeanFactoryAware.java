package com.tiny.springframework.bean.factory;

import com.tiny.springframework.bean.exception.BeansException;

/**
 * @Descrpition 实现此接口，可以感知到所属的BeanFactory
 * @Date 2024/9/4
 */
public interface BeanFactoryAware extends Aware{
    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}

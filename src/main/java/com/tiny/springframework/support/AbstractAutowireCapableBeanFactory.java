package com.tiny.springframework.support;

import com.tiny.springframework.config.BeanDefinition;
import com.tiny.springframework.exception.BeansException;

/**
 * 实现 Bean 实例的创建方法
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{
    private InstantiationStrategy instantiationStrategy = new ReflectionInstantiationStrategy();


    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException{
        Object bean = instantiationStrategy.instantiate(beanDefinition, beanName, args);
        addSingleton(beanName, bean);
        return bean;
    }

}

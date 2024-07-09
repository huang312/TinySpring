package com.tiny.springframework.support;

import com.tiny.springframework.config.BeanDefinition;
import com.tiny.springframework.exception.BeansException;

/**
 * 实现 Bean 实例的创建方法
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        Object bean = null;
        try{
            Class beanClass = beanDefinition.getBeanClass();
            bean = beanClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BeansException("failed to instance bean: "+ beanName, e);
        }
        return bean;
    }
}

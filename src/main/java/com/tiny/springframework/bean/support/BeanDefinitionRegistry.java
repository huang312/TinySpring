package com.tiny.springframework.bean.support;

import com.tiny.springframework.bean.config.BeanDefinition;
import com.tiny.springframework.bean.exception.BeansException;

/**
 * BeanDefinition的注册
 */
public interface BeanDefinitionRegistry {
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) throws BeansException;

    boolean containsBeanDefinition(String beanName);
}

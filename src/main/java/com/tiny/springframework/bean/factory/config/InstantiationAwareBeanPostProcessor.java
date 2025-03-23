package com.tiny.springframework.bean.factory.config;

import com.tiny.springframework.bean.PropertyValues;
import com.tiny.springframework.bean.exception.BeansException;

/**
 * @Descrpition
 * @Date 2025/3/10
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {

    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException;

    boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException;

    PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeansException;
}

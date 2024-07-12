package com.tiny.springframework.support;

import com.tiny.springframework.config.BeanDefinition;
import com.tiny.springframework.exception.BeansException;

/**
 * 实例化策略
 */
public interface InstantiationStrategy {
    /**
     * Bean的实例化方法
     * @param beanDefinition
     * @param beanName
     * @param args
     * @return
     */
    Object instantiate(BeanDefinition beanDefinition, String beanName, Object[] args) throws BeansException;
}

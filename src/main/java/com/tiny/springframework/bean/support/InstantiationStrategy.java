package com.tiny.springframework.bean.support;

import com.tiny.springframework.bean.config.BeanDefinition;
import com.tiny.springframework.bean.exception.BeansException;

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

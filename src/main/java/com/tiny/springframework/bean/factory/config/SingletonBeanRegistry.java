package com.tiny.springframework.bean.factory.config;

import com.tiny.springframework.bean.exception.BeansException;

/**
 * 单例获取 Bean 实例
 */
public interface SingletonBeanRegistry {
    Object getSingleton(String name) throws BeansException;

    void addSingleton(String beanName, Object singleton);

    void registerSingleton(String beanName, Object singleton);
}

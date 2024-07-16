package com.tiny.springframework.bean.config;

/**
 * 单例获取 Bean 实例
 */
public interface SingletonBeanRegistry {
    Object getSingleton(String name);
}

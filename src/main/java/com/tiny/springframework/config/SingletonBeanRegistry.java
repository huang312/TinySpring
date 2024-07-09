package com.tiny.springframework.config;

/**
 * 单例获取 Bean 实例
 */
public interface SingletonBeanRegistry {
    Object getSingleton(String name);
}

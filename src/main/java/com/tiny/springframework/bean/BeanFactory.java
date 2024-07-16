package com.tiny.springframework.bean;

import com.tiny.springframework.bean.exception.BeansException;

/**
 * Bean 工厂，提供 Bean 获取功能
 */
public interface BeanFactory {
    /**
     * 通过 Bean 名称获取 Bean 实例（Object类型）
     * @param name Bean 名称
     * @return Bean 实例
     */
    Object getBean(String name) throws BeansException;

    /**
     * 通过 Bean 的名称和参数获取 Bean 实例
     * @param name Bean 名称
     * @param args 构造 Bean 的参数
     * @return Bean 实例
     * @throws BeansException
     */
    Object getBean(String name, Object... args) throws BeansException;

    /**
     * 通过 Bean 的名称获取指定类型的Bean实例
     * @param name
     * @param clazz
     * @return
     * @param <T>
     * @throws BeansException
     */
    <T> T getBean(String name, Class<T> clazz) throws BeansException;
}

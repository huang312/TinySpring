package com.tiny.springframework.bean.config;

import com.tiny.springframework.bean.ConfigurableListableBeanFactory;
import com.tiny.springframework.bean.exception.BeansException;

/**
 * BeanDefinition 的后处理器接口
 */
public interface BeanFactoryPostProcessor {

    /**
     * 在所有的 BeanDefinition 加载完成后，实例化 Bean 对象之前，提供修改 BeanDefinition 属性的机制
     *
     * @param beanFactory
     * @throws BeansException
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}

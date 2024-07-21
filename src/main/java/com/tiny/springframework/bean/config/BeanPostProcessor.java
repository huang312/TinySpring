package com.tiny.springframework.bean.config;

import com.tiny.springframework.bean.exception.BeansException;

/**
 * Bean 的后处理接口
 */
public interface BeanPostProcessor {
    /**
     * 在Bean对象执行初始化方法之前执行此方法
     *
     * @param bean bean
     * @param beanName beanName
     * @return
     * @throws BeansException
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

    /**
     * 在Bean对象执行初始化方法之后执行此方法
     *
     * @param bean bean
     * @param beanName beanName
     * @return
     * @throws BeansException
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;
}

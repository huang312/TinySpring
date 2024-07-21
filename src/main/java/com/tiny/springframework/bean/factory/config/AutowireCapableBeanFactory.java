package com.tiny.springframework.bean.factory.config;

import com.tiny.springframework.bean.exception.BeansException;
import com.tiny.springframework.bean.factory.BeanFactory;

/**
 * 自动化处理Bean工厂配置的接口
 */
public interface AutowireCapableBeanFactory extends BeanFactory {
    /**
     * 执行Bean实例化的前置处理
     *
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException;

    /**
     * 执行Bean实例化的后置处理
     *
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException;

}

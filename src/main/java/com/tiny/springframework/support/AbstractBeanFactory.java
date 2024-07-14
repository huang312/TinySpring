package com.tiny.springframework.support;

import com.tiny.springframework.BeanFactory;
import com.tiny.springframework.config.BeanDefinition;
import com.tiny.springframework.exception.BeansException;

/**
 * 模板模式实现 getBean 方法
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {
    @Override
    public Object getBean(String name) throws BeansException {
        Object bean = getSingleton(name);
        if(bean != null) return bean;
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return createBean(name, beanDefinition, null);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        Object bean = getSingleton(name);
        if(bean != null) return bean;
        BeanDefinition beanDefinition = getBeanDefinition(name);

        return createBean(name, beanDefinition, args);
    }

    /**
     * 获取 BeanDefinition
     * @param name
     * @return
     * @throws BeansException
     */
    protected abstract BeanDefinition getBeanDefinition(String name) throws BeansException;

    /**
     * 创建 Bean 实例
     * @param beanName
     * @param beanDefinition
     * @param args bean的构造器参数
     * @return
     * @throws BeansException
     */
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;
}

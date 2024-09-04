package com.tiny.springframework.context.support;

import com.tiny.springframework.bean.exception.BeansException;
import com.tiny.springframework.bean.factory.config.BeanPostProcessor;
import com.tiny.springframework.context.ApplicationContext;
import com.tiny.springframework.context.ApplicationContextAware;

/**
 * @Descrpition Aware接口的包装处理器
 * @Date 2024/9/4
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {
    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ApplicationContextAware) {
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}

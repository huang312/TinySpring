package com.tiny.springframework.bean.factory.config;

import com.tiny.springframework.bean.factory.HierarchicalBeanFactory;

/**
 * 一个配置化接口
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory {

    String SCOPE_SINGLETON = "singleton";
    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}

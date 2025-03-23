package com.tiny.springframework.bean.factory.config;

import com.tiny.springframework.bean.factory.HierarchicalBeanFactory;
import com.tiny.springframework.utils.StringValueResolver;

/**
 * 一个配置化接口
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";
    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    void addEmbeddedValueResolver(StringValueResolver valueResolver);

    String resolveEmbeddedValue(String value);
}

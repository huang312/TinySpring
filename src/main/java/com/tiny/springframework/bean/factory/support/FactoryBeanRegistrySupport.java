package com.tiny.springframework.bean.factory.support;

import com.tiny.springframework.bean.exception.BeansException;
import com.tiny.springframework.bean.factory.FactoryBean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Descrpition
 * @Date 2024/9/7
 */
public abstract class FactoryBeanRegistrySupport extends DefaultSingletonBeanRegistry{
    /**
     * Cache of singleton objects created by FactoryBeans
     */
    private final Map<String, Object> factoryBeanObjectCache = new ConcurrentHashMap<>();

    protected Object getCachedObjectForFactoryBean(String beanName) {
        return this.factoryBeanObjectCache.get(beanName);
    }

    protected Object getObjectFromFactoryBean(FactoryBean factory, String beanName) throws BeansException {
        if (factory.isSingleton()) {
            Object object = this.factoryBeanObjectCache.get(beanName);
            if (object == null) {
                object = doGetObjectFromFactoryBean(factory, beanName);
                this.factoryBeanObjectCache.put(beanName, object);
            }
            return object;
        } else {
            return doGetObjectFromFactoryBean(factory, beanName);
        }
    }

    private Object doGetObjectFromFactoryBean(final FactoryBean factory, String beanName) throws BeansException {
        try {
            return factory.getObject();
        } catch (Exception e) {
            throw new BeansException("FactoryBean threw exception on object[" + beanName + "]", e);
        }
    }
}

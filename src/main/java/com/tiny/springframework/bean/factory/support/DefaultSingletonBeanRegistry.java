package com.tiny.springframework.bean.factory.support;

import cn.hutool.core.lang.hash.Hash;
import com.tiny.springframework.bean.exception.BeansException;
import com.tiny.springframework.bean.factory.DisposableBean;
import com.tiny.springframework.bean.factory.ObjectFactory;
import com.tiny.springframework.bean.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 默认的 Bean 单例注册器实现
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    /**
     * 实例化的 Bean 容器
     */
    private Map<String, Object> singletonBeanMap = new HashMap<>();

    private Map<String, Object> earlySingletonBeanMap = new HashMap<>();

    private Map<String, ObjectFactory<?>> singletonFactories = new HashMap<>();

    /**
     * 销毁方法
     */
    private Map<String, DisposableBean> disposableBeanMap = new HashMap<>();

    @Override
    public Object getSingleton(String name) throws BeansException {
        Object singletonObject = singletonBeanMap.get(name);
        if (null == singletonObject) {
            singletonObject = earlySingletonBeanMap.get(name);
            if (null == singletonObject) {
                ObjectFactory<?> singletonFactory = singletonFactories.get(name);
                if (singletonFactory != null) {
                    singletonObject = singletonFactory.getObject();
                    earlySingletonBeanMap.put(name, singletonObject);
                    singletonFactories.remove(name);
                }
            }
        }
        return singletonObject;
    }

    public void registerSingleton(String beanName, Object singletonObject) {
        singletonBeanMap.put(beanName, singletonObject);
        earlySingletonBeanMap.remove(beanName);
        singletonFactories.remove(beanName);
    }

    protected void addSingletonFactory(String beanName, ObjectFactory<?> singletonFactory) {
        if (!this.singletonBeanMap.containsKey(beanName)) {
            this.singletonFactories.put(beanName, singletonFactory);
            this.earlySingletonBeanMap.remove(beanName);
        }
    }

    /**
     * 添加新的单例 Bean 实例，只有容器可以实例化 Bean, 所以访问权限设置为 protect
     * @param beanName Bean 名称
     * @param singletonObject 单例的 Bean 实例
     */
    @Override
    public void addSingleton(String beanName, Object singletonObject) {
        this.singletonBeanMap.put(beanName, singletonObject);
    }

    /**
     * 注册Bean实例的销毁方法
     *
     * @param beanName
     * @param bean
     */
    public void registerDisposableBean(String beanName, DisposableBean bean) {
        disposableBeanMap.put(beanName, bean);
    }

    /**
     * 执行所有已注册的Bean实例的销毁方法
     *
     * @throws BeansException
     */
    public void destroySingletons() throws BeansException {
        for (String beanName : disposableBeanMap.keySet()) {
            DisposableBean disposableBean = disposableBeanMap.get(beanName);
//            disposableBeanMap.remove(beanName);
            try{
                disposableBean.destroy();
            } catch (Exception e) {
                throw new BeansException("Destroy method on bean with name '" + beanName + "' throw an exception");
            }
        }

    }

}

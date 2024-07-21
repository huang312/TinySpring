package com.tiny.springframework.bean.factory;

import com.tiny.springframework.bean.exception.BeansException;

import java.util.Map;

/**
 * 扩展 BeanFactory 接口的接口
 */
public interface ListableBeanFactory extends BeanFactory{
    /**
     * 按照类型返回 Bean 实例
     *
     * @param type Bean的类型
     * @return 所有类型未 type 的Bean实例
     * @param <T> 泛型参数
     * @throws BeansException
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    /**
     * 返回注册表中所有的 Bean 的名称
     *
     * @return
     */
    String[] getBeanDefinitionNames();
}

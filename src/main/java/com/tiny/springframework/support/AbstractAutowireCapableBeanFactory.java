package com.tiny.springframework.support;

import com.tiny.springframework.PropertyValue;
import com.tiny.springframework.PropertyValues;
import com.tiny.springframework.config.BeanDefinition;
import com.tiny.springframework.config.BeanReference;
import com.tiny.springframework.exception.BeansException;
import com.tiny.springframework.utils.BeanUtil;

/**
 * 实现 Bean 实例的创建方法
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{
    private InstantiationStrategy instantiationStrategy = new ReflectionInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException{
        Object bean = instantiationStrategy.instantiate(beanDefinition, beanName, args);
        propertyInjection(beanDefinition, bean);
        addSingleton(beanName, bean);
        return bean;
    }

    /**
     * 属性注入
     * @param beanDefinition
     * @param bean
     */
    private void propertyInjection(BeanDefinition beanDefinition, Object bean) throws BeansException {
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        for (PropertyValue pv : propertyValues.getPropertyValueList()) {
            Object value = pv.getValue();
            if(pv.getValue() instanceof BeanReference){
                value = getBean(pv.getName());
            }
            BeanUtil.setField(bean, value, pv.getName());
        }
    }

}

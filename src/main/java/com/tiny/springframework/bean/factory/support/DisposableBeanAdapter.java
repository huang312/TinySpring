package com.tiny.springframework.bean.factory.support;

import com.tiny.springframework.bean.exception.BeansException;
import com.tiny.springframework.bean.factory.DisposableBean;
import com.tiny.springframework.bean.factory.config.BeanDefinition;

import java.lang.reflect.Method;

/**
 * 销毁方法使用适配器包装
 */
public class DisposableBeanAdapter implements DisposableBean {
    private final Object bean;
    private final String beanName;
    private String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }
    @Override
    public void destroy() throws Exception {
        // 1. 如果实现了接口DisposableBean
        if (bean instanceof DisposableBean) {
            ((DisposableBean) bean).destroy();
        }

        // 2.通过配置信息配置销毁方法
        if (destroyMethodName != null && !(bean instanceof DisposableBean)) {
            Method destroyMethod = bean.getClass().getDeclaredMethod(destroyMethodName);
            if (null == destroyMethod) {
                throw new BeansException("Could not find a destroy method named '"
                        + destroyMethodName + "' in bean [" + beanName + "].");
            }
            destroyMethod.invoke(bean);
        }

    }
}

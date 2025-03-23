package com.tiny.springframework.bean.factory.support;

import cn.hutool.core.util.StrUtil;
import com.tiny.springframework.bean.PropertyValue;
import com.tiny.springframework.bean.PropertyValues;
import com.tiny.springframework.bean.factory.*;
import com.tiny.springframework.bean.factory.config.*;
import com.tiny.springframework.bean.exception.BeansException;
import com.tiny.springframework.bean.factory.utils.BeanUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * 实现 Bean 实例的创建方法
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {
    private InstantiationStrategy instantiationStrategy = new ReflectionInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException{
        // 判断是否需要返回代理对象
        Object bean = resolveBeforeInstantiation(beanName, beanDefinition);
        if (null != bean) {
            return bean;
        }
        return doCreateBean(beanName, beanDefinition, args);
    }

    protected Object doCreateBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean = null;
        try {
            // 创建Bean实例
            bean = instantiationStrategy.instantiate(beanDefinition, beanName, args);
            // 在设置Bean属性之前，允许BeanPostProcessor修改属性值
            applyBeanPostProcessorsBeforeApplyingPropertyValues(beanName, bean, beanDefinition);
            // 给 Bean 填充属性
            propertyInjection(beanDefinition, bean);
            // 执行bean的初始化方法和 BeanPostProcessor 的前置和后置处理方法
            bean = initializeBean(beanName, bean, beanDefinition);
            // 注册实现了 DisposableBean 接口的Bean对象
            registerDisposableBeanIfNecessary(beanName, bean, beanDefinition);
            // Bean的作用域为singleton时保存到内存中
            if(beanDefinition.isSingleton()){
                addSingleton(beanName, bean);
            }
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed [" + beanDefinition + "]", e);
        }
        return bean;
    }

    protected void applyBeanPostProcessorsBeforeApplyingPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) throws BeansException {
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            if (processor instanceof InstantiationAwareBeanPostProcessor) {
                PropertyValues pvs = ((InstantiationAwareBeanPostProcessor) processor).postProcessPropertyValues(beanDefinition.getPropertyValues(), bean, beanName);
                if (null != pvs) {
                    for (PropertyValue propertyValue : pvs.getPropertyValues()) {
                        beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
                    }
                }
            }
        }
    }

    protected Object resolveBeforeInstantiation(String beanName, BeanDefinition beanDefinition) throws BeansException {
        Object bean = applyBeanPostProcessorBeforeInstantiation(beanDefinition.getBeanClass(), beanName);
        if (null != bean) {
            bean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
        }
        return bean;
    }

    protected Object applyBeanPostProcessorBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor) {
                Object result = ((InstantiationAwareBeanPostProcessor) beanPostProcessor).postProcessBeforeInstantiation(
                        beanClass, beanName
                );
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    protected void registerDisposableBeanIfNecessary(String beanName, Object bean, BeanDefinition beanDefinition) {
        if(!beanDefinition.isSingleton()) return;
        if (bean instanceof DisposableBean || StrUtil.isNotEmpty(beanDefinition.getDestroyMethodName())) {
            registerDisposableBean(beanName, new DisposableBeanAdapter(bean, beanName, beanDefinition));
        }
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
                BeanReference beanReference = (BeanReference) pv.getValue();
                value = getBean(beanReference.getBeanName());
            }
            BeanUtil.setField(bean, value, pv.getName());
        }
    }

    /**
     * 初始化bean
     *
     * @param beanName
     * @param bean
     * @param beanDefinition
     * @return
     * @throws BeansException
     */
    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) throws BeansException{
        // invokeAwareMethods
        if (bean instanceof Aware) {
            if (bean instanceof BeanFactoryAware) {
                ((BeanFactoryAware) bean).setBeanFactory(this);
            }
            if(bean instanceof BeanClassLoaderAware){
                ((BeanClassLoaderAware) bean).setBeanClassLoader(getBeanClassLoader());
            }
            if(bean instanceof BeanNameAware){
                ((BeanNameAware) bean).setBeanName(beanName);
            }
        }
        // 1.执行BeanPostProcessor 前置处理
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);

        // 2.执行init方法
        try{
            invokeInitMethods(beanName, wrappedBean, beanDefinition);
        } catch (Exception e){
            throw new BeansException("Invocation of init method of bean [" + beanName +"] failed", e);
        }

        // 3. 执行Bean对象的初始化方法
        wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
        return wrappedBean;
    }

    /**
     * 调用 bean 的初始化方法
     * @param beanName
     * @param bean
     * @param beanDefinition
     * @throws Exception
     */
    private void invokeInitMethods(String beanName, Object bean, BeanDefinition beanDefinition) throws Exception {
        // 1. 执行接口initializingBean 的方法afterPropertiesSet
        if (bean instanceof InitializingBean){
            ((InitializingBean) bean).afterPropertiesSet();
        }
        // 2.配置信息 init-method 避免执行多次初始化方法
        String initMethodName = beanDefinition.getInitMethodName();
        if(StrUtil.isNotEmpty(initMethodName) && !(bean instanceof  InitializingBean)){
            Method initMethod = beanDefinition.getBeanClass().getMethod(initMethodName);
            if (null == initMethod) {
                throw new BeansException("Could not find an init method named '" + initMethodName +"' on bean with name [" + beanName + "]");
            }
            initMethod.invoke(bean);
        }

    }

    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessBeforeInitialization(existingBean, beanName);
            if(null == current) return result;
            result = current;
        }
        return result;
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessAfterInitialization(existingBean, beanName);
            if(null == current) return result;
            result = current;
        }
        return result;
    }
}

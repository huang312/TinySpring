package com.tiny.springframework.bean.factory.config;

import com.tiny.springframework.bean.PropertyValues;

/**
 * BeanDefinition
 */
public class BeanDefinition {
    /**
     * 单例作用域
     */
    String SCOPE_SINGLETON = ConfigurableBeanFactory.SCOPE_SINGLETON;
    /**
     * 原型作用域
     */
    String SCOPE_PROTOTYPE = ConfigurableBeanFactory.SCOPE_PROTOTYPE;
    /**
     * Bean 类型
     */
    private Class beanClass;
    /**
     * Bean 的属性
     */
    private PropertyValues propertyValues;

    /** Bean 实例初始化方法名 */
    private String initMethodName;

    /** Bean 实例销毁方法名 */
    private String destroyMethodName;

    /**
     * 对象作用域
     */
    private String scope = SCOPE_SINGLETON;

    /**
     * 是否是单例作用域
     */
    private boolean singleton = true;

    /**
     * 是否是原型作用域
     */
    private boolean prototype = false;

    public void setScope(String scope) {
        this.scope = scope;
        this.singleton = SCOPE_SINGLETON.equals(scope);
        this.prototype = SCOPE_PROTOTYPE.equals(scope);
    }

    public BeanDefinition(Class beanClass){
        this(beanClass, null);
    }

    public BeanDefinition(Class beanClass, PropertyValues propertyValues){
        this.beanClass = beanClass;
        this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
    }

    public Class getBeanClass(){
        return this.beanClass;
    }

    public void setBeanClass(Class beanClass){
        this.beanClass = beanClass;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

    public PropertyValues getPropertyValues() {
        return this.propertyValues;
    }

    public String getInitMethodName() {
        return initMethodName;
    }

    public void setInitMethodName(String initMethodName) {
        this.initMethodName = initMethodName;
    }

    public String getDestroyMethodName() {
        return destroyMethodName;
    }

    public void setDestroyMethodName(String destroyMethodName) {
        this.destroyMethodName = destroyMethodName;
    }

}

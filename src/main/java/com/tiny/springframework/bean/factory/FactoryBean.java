package com.tiny.springframework.bean.factory;

/**
 * FactoryBean interface
 *
 * @Descrpition
 * @Date 2024/9/7
 */
public interface FactoryBean<T> {
    /**
     * get object
     *
     * @return
     * @throws Exception
     */
    T getObject() throws Exception;

    /**
     * get object type
     *
     * @return Class of the object
     */
    Class<?> getObjectType();

    /**
     * object is singleton
     *
     * @return true if object is singleton, otherwise false
     */
    boolean isSingleton();
}

package com.tiny.springframework.bean.factory;

/**
 * @Descrpition 实现此接口，即能感知到所属的ClassLoader
 * @Date 2024/9/4
 */
public interface BeanClassLoaderAware extends Aware {

    void setBeanClassLoader(ClassLoader classLoader);

}

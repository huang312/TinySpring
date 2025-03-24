package com.tiny.springframework.bean.factory;

import com.tiny.springframework.bean.exception.BeansException;

/**
 * @Descrpition
 * @Date 2025/3/24
 */
public interface ObjectFactory<T> {
    T getObject() throws BeansException;
}

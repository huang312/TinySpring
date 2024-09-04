package com.tiny.springframework.context;

import com.tiny.springframework.bean.exception.BeansException;

/**
 * @Descrpition 实现此接口，即能感知到所属的ApplicationContext
 * @Date 2024/9/4
 */
public interface ApplicationContextAware {
    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}

package com.tiny.springframework.bean.factory;

import com.tiny.springframework.bean.exception.BeansException;

public interface InitializingBean {
    /**
     * Bean 处理了属性填充后调用
     *
     * @throws Exception
     */
    void afterPropertiesSet() throws BeansException;
}

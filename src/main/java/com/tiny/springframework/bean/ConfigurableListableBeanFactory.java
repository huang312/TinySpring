package com.tiny.springframework.bean;

import com.tiny.springframework.bean.config.ConfigurableBeanFactory;

/**
 * 提供分析和修改Bean以及余弦实例化的操作接口
 */
public interface ConfigurableListableBeanFactory extends ConfigurableBeanFactory, ListableBeanFactory, AutowireCapableBeanFactory {

}

package com.tiny.springframework.bean.factory;

import com.tiny.springframework.bean.factory.config.AutowireCapableBeanFactory;
import com.tiny.springframework.bean.factory.config.ConfigurableBeanFactory;

/**
 * 提供分析和修改Bean以及余弦实例化的操作接口
 */
public interface ConfigurableListableBeanFactory extends ConfigurableBeanFactory, ListableBeanFactory, AutowireCapableBeanFactory {

}

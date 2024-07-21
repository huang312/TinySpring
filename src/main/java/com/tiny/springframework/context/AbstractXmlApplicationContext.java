package com.tiny.springframework.context;

import com.tiny.springframework.bean.exception.BeansException;
import com.tiny.springframework.bean.factory.support.DefaultListableBeanFactory;
import com.tiny.springframework.bean.factory.xml.XmlBeanDefinitionReader;

/**
 * 上下文中对配置信息的加载
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext{
    @Override
    protected void loadBeanDefinition(DefaultListableBeanFactory beanFactory) throws BeansException {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if (null != configLocations) {
            xmlBeanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    protected abstract String[] getConfigLocations();
}

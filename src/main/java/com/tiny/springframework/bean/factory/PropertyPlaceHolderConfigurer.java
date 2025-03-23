package com.tiny.springframework.bean.factory;

import com.tiny.springframework.bean.PropertyValue;
import com.tiny.springframework.bean.PropertyValues;
import com.tiny.springframework.bean.exception.BeansException;
import com.tiny.springframework.bean.factory.config.BeanDefinition;
import com.tiny.springframework.bean.factory.config.BeanFactoryPostProcessor;
import com.tiny.springframework.core.io.DefaultResourceLoader;
import com.tiny.springframework.core.io.Resource;
import com.tiny.springframework.utils.StringValueResolver;

import java.io.IOException;
import java.util.Properties;

/**
 * @Descrpition
 * @Date 2025/3/19
 */
public class PropertyPlaceHolderConfigurer implements BeanFactoryPostProcessor {
    /**
     * Default placeholder prefix: {@value}
     */
    public static final String DEFAULT_PLACEHOLDER_PREFIX = "${";

    /**
     * Default placeholder suffix: {@value}
     */
    public static final String DEFAULT_PLACEHOLDER_SUFFIX = "}";

    private String location;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // 加载属性文件
        try {
            DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
            Resource resource = resourceLoader.getResource(location);
            Properties properties = new Properties();
            properties.load(resource.getInputStream());

            String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
            for (String beanName : beanDefinitionNames) {
                BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);

                PropertyValues propertyValues = beanDefinition.getPropertyValues();
                for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                    Object value = propertyValue.getValue();
                    if (!(value instanceof String)) continue;

                    value = resolvePlaceholder((String) value, properties);
                    propertyValues.addPropertyValue(new PropertyValue(propertyValue.getName(), value));
                }
            }

            PlaceholderResolvingStringValueResolver resolver = new PlaceholderResolvingStringValueResolver(properties);
            beanFactory.addEmbeddedValueResolver(resolver);
        } catch (IOException e) {
            throw new BeansException("Could not load properties", e);
        }
    }

    public String resolvePlaceholder(String strVal, Properties properties) {
        StringBuffer buffer = new StringBuffer(strVal);
        int startIndex = buffer.indexOf(DEFAULT_PLACEHOLDER_PREFIX);
        int stopIndex = buffer.indexOf(DEFAULT_PLACEHOLDER_SUFFIX);
        if (startIndex != -1 && stopIndex != -1 && startIndex < stopIndex) {
            String propKey = strVal.substring(startIndex+2, stopIndex);
            String propVal = properties.getProperty(propKey);
            buffer.replace(startIndex, stopIndex+1, propVal);
        }
        return buffer.toString();
    }

    public void setLocation(String location) {
        this.location = location;
    }

    private class PlaceholderResolvingStringValueResolver implements StringValueResolver {
        private final Properties properties;

        private PlaceholderResolvingStringValueResolver(Properties properties) {
            this.properties = properties;
        }

        @Override
        public String resolveStringValue(String strVal) {
            return PropertyPlaceHolderConfigurer.this.resolvePlaceholder(strVal, properties);
        }
    }
}

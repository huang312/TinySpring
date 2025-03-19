package com.tiny.springframework.context.annotation;

import cn.hutool.core.util.StrUtil;
import com.tiny.springframework.bean.exception.BeansException;
import com.tiny.springframework.bean.factory.config.BeanDefinition;
import com.tiny.springframework.bean.factory.support.BeanDefinitionRegistry;
import com.tiny.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * @Descrpition
 * @Date 2025/3/19
 */
public class ClasspathBeanDefinitionScanner extends ClasspathBeanDefinitionCandidateComponentProvider{
    private BeanDefinitionRegistry registry;
    public ClasspathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }
    public void doScan(String... basePackages) throws BeansException {
        for (String basePackage : basePackages) {
            Set<BeanDefinition> candidateComponents = findCandidateComponents(basePackage);
            for (BeanDefinition beanDefinition : candidateComponents) {
                beanDefinition.setScope(resolveBeanScope(beanDefinition));
                registry.registerBeanDefinition(determineBeanName(beanDefinition), beanDefinition);
            }
        }
    }

    private String resolveBeanScope(BeanDefinition beanDefinition) {
        Class<?> beanClass = beanDefinition.getBeanClass();
        Scope scope = beanClass.getAnnotation(Scope.class);
        if (scope != null) {
            return StrUtil.lowerFirst(scope.value());
        }
        return StrUtil.EMPTY;
    }

    private String determineBeanName(BeanDefinition beanDefinition) {
        Class<?> beanClass = beanDefinition.getBeanClass();
        Component component = beanClass.getAnnotation(Component.class);
        String value = component.value();
        if (StrUtil.isEmpty(value)) {
            value = beanClass.getSimpleName();
        }
        return value;
    }

}

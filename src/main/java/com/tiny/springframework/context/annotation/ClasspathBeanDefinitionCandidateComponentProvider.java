package com.tiny.springframework.context.annotation;

import cn.hutool.core.util.ClassUtil;
import com.tiny.springframework.bean.factory.config.BeanDefinition;
import com.tiny.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @Descrpition
 * @Date 2025/3/19
 */
public class ClasspathBeanDefinitionCandidateComponentProvider {
    public Set<BeanDefinition> findCandidateComponents(String basePackage) {
        Set<BeanDefinition> candidates = new LinkedHashSet<>();
        Set<Class<?>> classSet = ClassUtil.scanPackage(basePackage);
        for (Class<?> clazz : classSet) {
            if (clazz.isAnnotationPresent(Component.class)) {
                candidates.add(new BeanDefinition(clazz));
            }
        }
        return candidates;
    }
}

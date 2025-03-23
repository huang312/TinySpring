package com.tiny.springframework.bean.factory.support;

import com.tiny.springframework.bean.factory.BeanFactory;
import com.tiny.springframework.bean.factory.FactoryBean;
import com.tiny.springframework.bean.factory.config.BeanDefinition;
import com.tiny.springframework.bean.exception.BeansException;
import com.tiny.springframework.bean.factory.config.BeanPostProcessor;
import com.tiny.springframework.bean.factory.config.ConfigurableBeanFactory;
import com.tiny.springframework.utils.ClassUtil;
import com.tiny.springframework.utils.StringValueResolver;

import java.util.ArrayList;
import java.util.List;

/**
 * 模板模式实现 getBean 方法
 */
public abstract class AbstractBeanFactory extends FactoryBeanRegistrySupport implements ConfigurableBeanFactory {

    /** BeanPostProcessors to apply in createBean */
    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    private final List<StringValueResolver> embeddedValueResolvers = new ArrayList<>();

    @Override
    public void addEmbeddedValueResolver(StringValueResolver valueResolver) {
        this.embeddedValueResolvers.add(valueResolver);
    }

    @Override
    public String resolveEmbeddedValue(String value) {
        String result = value;
        for (StringValueResolver resolver : embeddedValueResolvers) {
            result = resolver.resolveStringValue(result);
        }
        return result;
    }

    /**
     * 类加载器
     */
    private ClassLoader beanClassLoader = ClassUtil.getDefaultClassLoader();
    @Override
    public Object getBean(String name) throws BeansException {
        return doGetBean(name);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return doGetBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> clazz) throws BeansException {
        return (T) getBean(name);
    }

    private Object doGetBean(String name, Object... args) throws BeansException {
        Object bean = getSingleton(name);
        if(bean != null) return getObjectForBeanInstance(bean, name);
        BeanDefinition beanDefinition = getBeanDefinition(name);

        bean = createBean(name, beanDefinition, args);
        return getObjectForBeanInstance(bean, name);
    }

    /**
     *
     * @param beanInstance
     * @param beanName
     * @return
     * @throws BeansException
     */
    private Object getObjectForBeanInstance(Object beanInstance, String beanName) throws BeansException {
        if (!(beanInstance instanceof FactoryBean)) {
            return beanInstance;
        }

        Object object = getCachedObjectForFactoryBean(beanName);
        if (object == null) {
            FactoryBean<?> factoryBean = (FactoryBean<?>) beanInstance;
            object = getObjectFromFactoryBean(factoryBean, beanName);
        }
        return object;


    }

    /**
     * 获取 BeanDefinition
     * @param name
     * @return
     * @throws BeansException
     */
    protected abstract BeanDefinition getBeanDefinition(String name) throws BeansException;

    /**
     * 创建 Bean 实例
     * @param beanName
     * @param beanDefinition
     * @param args bean的构造器参数
     * @return
     * @throws BeansException
     */
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    /**
     * 返回Bean实例处理器列表
     *
     * @return
     */
    public List<BeanPostProcessor> getBeanPostProcessors(){
        return this.beanPostProcessors;
    }

    /**
     * 获取类加载器
     * @return
     */
    public ClassLoader getBeanClassLoader(){
        return this.beanClassLoader;
    }
}

package com.tiny.springframework.bean.factory.xml;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import com.tiny.springframework.bean.PropertyValue;
import com.tiny.springframework.bean.factory.config.BeanDefinition;
import com.tiny.springframework.bean.factory.config.BeanReference;
import com.tiny.springframework.bean.exception.BeansException;
import com.tiny.springframework.bean.factory.support.AbstractBeanDefinitionReader;
import com.tiny.springframework.bean.factory.support.BeanDefinitionRegistry;
import com.tiny.springframework.context.annotation.ClasspathBeanDefinitionScanner;
import com.tiny.springframework.core.io.Resource;
import com.tiny.springframework.core.io.ResourceLoader;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * xml BeanDefinition读取器
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }
    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }
    @Override
    public void loadBeanDefinition(Resource resource) throws BeansException {
        try(InputStream inputStream = resource.getInputStream()){
            doLoadBeanDefinition(inputStream);
        } catch (IOException | ClassNotFoundException e) {
            throw new BeansException("IOException paring XML document from " + resource, e);
        } catch (DocumentException e) {
            throw new BeansException("DocumentException ", e);
        }
    }

    @Override
    public void loadBeanDefinition(Resource... resources) throws BeansException {
        for (Resource resource : resources) {
            loadBeanDefinition(resource);
        }
    }

    @Override
    public void loadBeanDefinition(String location) throws BeansException {
        Resource resource = getResourceLoader().getResource(location);
        loadBeanDefinition(resource);
    }

    @Override
    public void loadBeanDefinitions(String[] locations) throws BeansException {
        for (String location : locations) {
            loadBeanDefinition(location);
        }
    }

    private void doLoadBeanDefinition(InputStream inputStream) throws ClassNotFoundException, BeansException, DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        org.dom4j.Element root = document.getRootElement();

        // 解析 context:component-scan 标签，扫描包中的类并提取相关信息，用于组装 BeanDefinition
        org.dom4j.Element componentScan = root.element("component-scan");
        if (null != componentScan) {
            String scanPath = componentScan.attributeValue("base-package");
            if (StrUtil.isEmpty(scanPath)) {
                throw new BeansException("The value of base-package attribute can not be empty or null");
            }
            scanPackage(scanPath);
        }

        List<org.dom4j.Element> beanList = root.elements("bean");
        for (org.dom4j.Element bean : beanList) {

            String id = bean.attributeValue("id");
            String name = bean.attributeValue("name");
            String className = bean.attributeValue("class");
            String initMethod = bean.attributeValue("init-method");
            String destroyMethodName = bean.attributeValue("destroy-method");
            String beanScope = bean.attributeValue("scope");

            // 获取 Class，方便获取类中的名称
            Class<?> clazz = Class.forName(className);
            // 优先级 id > name
            String beanName = StrUtil.isNotEmpty(id) ? id : name;
            if (StrUtil.isEmpty(beanName)) {
                beanName = StrUtil.lowerFirst(clazz.getSimpleName());
            }

            // 定义Bean
            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            beanDefinition.setInitMethodName(initMethod);
            beanDefinition.setDestroyMethodName(destroyMethodName);

            if (StrUtil.isNotEmpty(beanScope)) {
                beanDefinition.setScope(beanScope);
            }

            List<org.dom4j.Element> propertyList = bean.elements("property");
            // 读取属性并填充
            for (Element property : propertyList) {
                // 解析标签：property
                String attrName = property.attributeValue("name");
                String attrValue = property.attributeValue("value");
                String attrRef = property.attributeValue("ref");
                // 获取属性值：引入对象、值对象
                Object value = StrUtil.isNotEmpty(attrRef) ? new BeanReference(attrRef) : attrValue;
                // 创建属性信息
                PropertyValue propertyValue = new PropertyValue(attrName, value);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }
            if (getRegistry().containsBeanDefinition(beanName)) {
                throw new BeansException("Duplicate beanName[" + beanName + "] is not allowed");
            }
            // 注册 BeanDefinition
            getRegistry().registerBeanDefinition(beanName, beanDefinition);
        }
    }

    private void scanPackage(String packages) throws BeansException {
        String[] strings = StrUtil.splitToArray(packages, ",");
        ClasspathBeanDefinitionScanner scanner = new ClasspathBeanDefinitionScanner(getRegistry());
        scanner.doScan(strings);
    }
}

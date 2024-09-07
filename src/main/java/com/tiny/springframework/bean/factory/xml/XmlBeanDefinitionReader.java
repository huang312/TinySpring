package com.tiny.springframework.bean.factory.xml;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import com.tiny.springframework.bean.PropertyValue;
import com.tiny.springframework.bean.factory.config.BeanDefinition;
import com.tiny.springframework.bean.factory.config.BeanReference;
import com.tiny.springframework.bean.exception.BeansException;
import com.tiny.springframework.bean.factory.support.AbstractBeanDefinitionReader;
import com.tiny.springframework.bean.factory.support.BeanDefinitionRegistry;
import com.tiny.springframework.core.io.Resource;
import com.tiny.springframework.core.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

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

    private void doLoadBeanDefinition(InputStream inputStream) throws ClassNotFoundException, BeansException {
        Document document = XmlUtil.readXML(inputStream);
        Element root = document.getDocumentElement();
        NodeList childNodes = root.getChildNodes();
        for(int i = 0; i < childNodes.getLength(); i++){
            Node node = childNodes.item(i);
            // 非<bean>标签
            if(!(node instanceof Element) || !"bean".equals(node.getNodeName())) continue;

            // 解析标签
            Element bean = (Element) childNodes.item(i);
            // Bean基本信息
            String id = bean.getAttribute("id");
            String name = bean.getAttribute("name");
            String className = bean.getAttribute("class");
            // 从xml配置文件中解析初始化方法和销毁方法
            String initMethod = bean.getAttribute("init-method");
            String destroyMethod = bean.getAttribute("destroy-method");
            // Bean的作用域
            String scope = bean.getAttribute("scope");

            Class<?> aClass = Class.forName(className);
            // bean的名称 优先级：id > name
            String beanName = StrUtil.isNotEmpty(id) ? id : name;
            if(StrUtil.isEmpty(beanName)){
                beanName = StrUtil.lowerFirst(aClass.getSimpleName());
            }

            // 定义Bean
            BeanDefinition beanDefinition = new BeanDefinition(aClass);
            // 设置初始化方法和销毁方法
            beanDefinition.setInitMethodName(initMethod);
            beanDefinition.setDestroyMethodName(destroyMethod);
            // 设置作用域
            if (StrUtil.isNotEmpty(scope)) {
                beanDefinition.setScope(scope);
            }
            for(int j = 0; j < bean.getChildNodes().getLength(); j++){
                Node propertyNode = bean.getChildNodes().item(j);
                // 非<property>标签
                if(!(propertyNode instanceof Element) || !"property".equals(propertyNode.getNodeName())){
                    continue;
                }
                Element property = ((Element) propertyNode);
                String attrName = property.getAttribute("name");
                String attrValue = property.getAttribute("value");
                String attrRef = property.getAttribute("ref");
                Object value = StrUtil.isNotEmpty(attrRef) ? new BeanReference(attrRef) : attrValue;
                // 创建属性信息
                PropertyValue propertyValue = new PropertyValue(attrName, value);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }
            if(getRegistry().containsBeanDefinition(beanName))
                throw new BeansException("Duplicated bean name ["+ beanName +"] is not allowed");
            // 注册BeanDefinition
            getRegistry().registerBeanDefinition(beanName, beanDefinition);
        }
    }
}

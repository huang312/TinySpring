package com.tiny.springframework;

import com.tiny.springframework.bean.PropertyValue;
import com.tiny.springframework.bean.PropertyValues;
import com.tiny.springframework.bean.UserDao;
import com.tiny.springframework.bean.UserService;
import com.tiny.springframework.bean.factory.config.BeanDefinition;
import com.tiny.springframework.bean.factory.config.BeanReference;
import com.tiny.springframework.bean.exception.BeansException;
import com.tiny.springframework.bean.factory.support.DefaultListableBeanFactory;
import com.tiny.springframework.bean.factory.xml.XmlBeanDefinitionReader;
import com.tiny.springframework.context.support.ClassPathXmlApplicationContext;
import com.tiny.springframework.processor.MyBeanFactoryPostProcessor;
import com.tiny.springframework.processor.MyBeanPostProcessor;
import org.junit.Test;

public class ApiTest {

    @Test
    public void test_beanFactory() throws BeansException {
        // 1.创建 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 2.创建 BeanDefinition
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        // 3.向BeanFactory注册Bean
        beanFactory.registerBeanDefinition("userService", beanDefinition);
        // 4.获取Bean
        UserService userService = (UserService) beanFactory.getBean("userService", "jack");
        // 5.测试结果
        userService.queryUserInfo();
    }

    @Test
    public void test_property_injection() throws BeansException {
        // 1.创建 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2.创建 BeanDefinition
        // UserDao 的 BeanDefinition
        PropertyValue userDaoPV = new PropertyValue("name", "jack");
        PropertyValues userDaoPVs = new PropertyValues();
        userDaoPVs.addPropertyValue(userDaoPV);
        BeanDefinition userDaoBeanDefinition = new BeanDefinition(UserDao.class, userDaoPVs);
        // UserService 的 BeanDefinition
        PropertyValue userServicePV = new PropertyValue("userDao", new BeanReference("userDao"));
        PropertyValues userServicePVs = new PropertyValues();
        userServicePVs.addPropertyValue(userServicePV);
        BeanDefinition userServiceBeanDefinition = new BeanDefinition(UserService.class, userServicePVs);

        // 3.向BeanFactory注册Bean
        beanFactory.registerBeanDefinition("userDao", userDaoBeanDefinition);
        beanFactory.registerBeanDefinition("userService", userServiceBeanDefinition);

        // 4.获取Bean
        UserService userService = (UserService) beanFactory.getBean("userService");

        // 5.测试结果
        userService.queryUserInfo();
    }

    @Test
    public void test_xml_reader() throws BeansException {
        // 1.创建BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2.创建xmlBeanDefinitionReader
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        xmlBeanDefinitionReader.loadBeanDefinition("classpath:spring.xml");

        // 3.获取bean
        UserService userService = beanFactory.getBean("userService", UserService.class);
        UserDao userDao = beanFactory.getBean("userDao", UserDao.class);
//        System.out.println(userDao.getName());
        userService.queryUserInfo();
    }

    @Test
    public void test_context() throws BeansException {
        // 1.初始化 BeanFactory
//        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-processor.xml");
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();
        // 2.获取Bean对象
        UserService userService = applicationContext.getBean("userService", UserService.class);
        System.out.println(userService.queryUserInfo());
    }

    @Test
    public void test_factory() throws BeansException {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2.读取配置文件&注册Bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinition("classpath:spring.xml");

        // 3.BeanDefinition加载完成 & Bean实例化之前，修改BeanDefinition的值
        MyBeanFactoryPostProcessor myBeanFactoryPostProcessor = new MyBeanFactoryPostProcessor();
        myBeanFactoryPostProcessor.postProcessBeanFactory(beanFactory);

        // 4.Bean实例化之后，修改Bean的属性信息
        MyBeanPostProcessor myBeanPostProcessor = new MyBeanPostProcessor();
        beanFactory.addBeanPostProcessor(myBeanPostProcessor);

        // 5.获取Bean对象调用方法
        UserService userService = beanFactory.getBean("userService", UserService.class);
        System.out.println(userService.queryUserInfo());
    }

    @Test
    public void test_aware() throws BeansException {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        // 2.获取Bean对象
        UserService userService = applicationContext.getBean("userService", UserService.class);
        String result = userService.queryUserInfo();
        System.out.println("测试结果："+result);
    }
}

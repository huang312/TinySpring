package com.tiny.springframework.bean;

import com.tiny.springframework.bean.exception.BeansException;
import com.tiny.springframework.bean.factory.*;
import com.tiny.springframework.context.ApplicationContext;
import com.tiny.springframework.context.ApplicationContextAware;

public class UserService implements InitializingBean, DisposableBean, BeanNameAware, BeanFactoryAware, ApplicationContextAware, BeanClassLoaderAware {
    private String id;
    private String company;
    private String location;
    private UserDao userDao;
    private IUserDao iUserDao;

    private ApplicationContext applicationContext;
    private BeanFactory beanFactory;

    public IUserDao getIUserDao() {
        return iUserDao;
    }

    public void setIUserDao(IUserDao iUserDao) {
        this.iUserDao = iUserDao;
    }

    public String queryUserInfo() {
        return iUserDao.queryUserName(id)+", 公司："+company+", 地点"+location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("执行：UserService.destroy");
    }

    @Override
    public void afterPropertiesSet() throws BeansException {
        System.out.println("执行：UserService.afterPropertiesSet");
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("ClassLoader: " + classLoader);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("BeanFactory: "+beanFactory);
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String beanName) {
        System.out.println("BeanName: " + beanName);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("ApplicationContext: "+applicationContext);
        this.applicationContext = applicationContext;
    }
}

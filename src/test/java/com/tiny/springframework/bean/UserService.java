package com.tiny.springframework.bean;

import com.tiny.springframework.bean.exception.BeansException;
import com.tiny.springframework.bean.factory.DisposableBean;
import com.tiny.springframework.bean.factory.InitializingBean;

public class UserService implements InitializingBean, DisposableBean {
    private String id;
    private String company;
    private String location;
    private UserDao userDao;

    public String queryUserInfo() {
        return userDao.queryUserName(id)+", 公司："+company+", 地点"+location;
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
}

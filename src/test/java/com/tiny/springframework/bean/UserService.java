package com.tiny.springframework.bean;

public class UserService {
    private UserDao userDao;
    public void queryUserInfo(){
        System.out.println("查询用户信息: " + userDao.getName());
    }
    public UserService(UserDao userDao){
        this.userDao = userDao;
    }
    public UserService(){

    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }
}

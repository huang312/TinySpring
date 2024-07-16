package com.tiny.springframework.bean;

public class UserService {
    private String id;
    private UserDao userDao;
    public void queryUserInfo(){
        System.out.println("查询用户 "+this.id+" 信息: " + userDao.getName());
    }
//    public UserService(UserDao userDao){
//        this.userDao = userDao;
//    }
    public UserService(){

    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}

package com.tiny.springframework.bean;

public class UserService {
    private String name;
    public void queryUserInfo(){
        System.out.println("查询用户信息: " + name);
    }
    public UserService(String name){
        this.name = name;
    }
    public UserService(){
        this.name = "empty constructor";
    }
}

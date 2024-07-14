package com.tiny.springframework.bean;

public class UserDao {
    private String name;

    public UserDao(){}

    public UserDao(String name){
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

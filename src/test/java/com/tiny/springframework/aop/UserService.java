package com.tiny.springframework.aop;

import java.util.Random;

/**
 * @Descrpition
 * @Date 2024/10/5
 */
public class UserService implements IUserService{
    @Override
    public String queryUserInfo() {
        try{
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "小红，100001，深圳";
    }

    @Override
    public String register(String userName) {
        try{
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "注册用户："+ userName + " success!";
    }
}

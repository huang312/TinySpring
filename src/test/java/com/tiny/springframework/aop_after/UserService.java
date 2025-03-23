package com.tiny.springframework.aop_after;

import com.tiny.springframework.bean.factory.annotation.Value;
import com.tiny.springframework.stereotype.Component;

import java.util.Random;

/**
 * @Descrpition
 * @Date 2025/3/23
 */
@Component
public class UserService implements IUserService{
    @Value("${token}")
    private String token;
    @Override
    public String queryUserInfo() {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "小傅哥，100001，深圳，" + token;
    }
}

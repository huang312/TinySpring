package com.tiny.springframework.field;

import com.tiny.springframework.bean.factory.annotation.Autowired;
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

    @Autowired
    private UserDao userDao;

    public String queryUserInfo() {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return userDao.queryUserName("10001") + "，" + token;
    }
}

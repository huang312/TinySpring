package com.tiny.springframework.dependency;

import com.tiny.springframework.bean.factory.annotation.Autowired;
import com.tiny.springframework.bean.factory.annotation.Value;
import com.tiny.springframework.stereotype.Component;

/**
 * @Descrpition
 * @Date 2025/3/24
 */
@Component
public class UserDao {
    @Autowired
    private UserService userService;

    @Value("${token}")
    private String token;

    public String queryUserInfo(String user) {
        return "userDao: " + token;
    }
}

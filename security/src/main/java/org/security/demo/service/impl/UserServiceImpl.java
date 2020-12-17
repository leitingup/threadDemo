package org.security.demo.service.impl;

import org.security.demo.entity.UserEntity;
import org.security.demo.service.UserService;
import org.security.demo.utils.JwtUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserEntity getByUsername(String userName) {
        List<String> authorities = new ArrayList<>();
        authorities.add("ALL");
        UserEntity userEntity = new UserEntity("noob","123",authorities);
        return userEntity;
    }

    @Override
    public String login(String userName, String password) {
        if ("noob".equals(userName)&&"123".equals(password)){
            return JwtUtils.createJwtToken(userName);
        }
        return "";
    }
}

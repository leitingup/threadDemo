package org.security.demo.service;

import org.security.demo.entity.UserEntity;

public interface UserService {

    UserEntity getByUsername(String userName);

    String login(String userName,String password);
}

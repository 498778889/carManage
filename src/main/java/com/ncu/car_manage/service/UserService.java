package com.ncu.car_manage.service;

import com.ncu.car_manage.pojo.User;

public interface UserService {
    boolean findUserByName(String userName);
    boolean register(User user);
    boolean login(String userName,String password,String salt);
    User findUserByUserName(String userName);
}

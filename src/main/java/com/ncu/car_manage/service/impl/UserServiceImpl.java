package com.ncu.car_manage.service.impl;

import com.ncu.car_manage.mapper.UserMapper;
import com.ncu.car_manage.pojo.User;
import com.ncu.car_manage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean findUserByName(String userName) {
        return userMapper.findByUserName(userName) != null;
    }

    @Override
    public boolean register(User user) {
        User existUser=userMapper.findByUserName(user.getUserName());
        if (existUser==null){
            if (userMapper.register(user)>=1){
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean login(String userName, String password) {
        return userMapper.login(userName, password)!=null;
    }

    @Override
    public User findUserByUserName(String userName) {
        return userMapper.findByUserName(userName);
    }
}

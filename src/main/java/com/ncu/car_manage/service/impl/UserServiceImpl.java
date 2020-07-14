package com.ncu.car_manage.service.impl;

import com.ncu.car_manage.mapper.UserMapper;
import com.ncu.car_manage.pojo.User;
import com.ncu.car_manage.service.UserService;
import com.ncu.car_manage.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

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
            String salt = UUID.randomUUID().toString();
            user.setSalt(salt);
            user.setPassword(MD5Utils.getMD5Str(user.getPassword(),salt));
            return userMapper.register(user) >= 1;
        }
        return false;
    }

    @Override
    public boolean login(String userName, String password,String salt) {
        password = MD5Utils.getMD5Str(password,salt);
        return userMapper.login(userName, password)!=null;
    }

    @Override
    public User findUserByUserName(String userName) {
        return userMapper.findByUserName(userName);
    }
}

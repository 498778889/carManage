package com.ncu.car_manage.mapper;

import com.ncu.car_manage.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    User findByUserName(@Param("userName") String userName);
    int register(User user);
    User login(@Param("userName") String userName, @Param("password") String password);
}

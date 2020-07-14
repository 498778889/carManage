package com.ncu.car_manage.pojo;

import lombok.Data;

@Data
public class User {
    int id;
    String userName;
    String password;
    String nickName;
    String realName;
    int sex;
    String salt;
}

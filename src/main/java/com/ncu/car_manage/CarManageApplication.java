package com.ncu.car_manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableRedisHttpSession
public class CarManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarManageApplication.class, args);
    }

}

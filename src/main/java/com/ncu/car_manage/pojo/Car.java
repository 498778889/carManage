package com.ncu.car_manage.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Car {
    int id;
    String brand;
    String color;
    int sites;
    float fuelConsumption;
    int dailyRent;
    String addPerson;
    String productionDate;
    Date addTime;
    String pic_url;
}

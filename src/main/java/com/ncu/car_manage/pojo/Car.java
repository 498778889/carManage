package com.ncu.car_manage.pojo;

import lombok.Data;

@Data
public class Car {
    int id;
    String brand;
    String color;
    int sites;
    float fuelConsumption;
    int dailyRent;
    String addPerson;
}

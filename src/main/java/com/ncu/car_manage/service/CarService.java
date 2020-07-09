package com.ncu.car_manage.service;

import com.github.pagehelper.PageInfo;
import com.ncu.car_manage.pojo.Car;

import java.util.List;

public interface CarService {
    List<Car> findAllCar();
    List<Car> findCarPage(int page,int size);
    Car findCarById(int id);
    boolean findCarExist(int id);
    boolean addCar(Car car);
    boolean updateCar(Car car);
    boolean deleteCar(int id);

    PageInfo<Car> findAll(int page,int size);
}

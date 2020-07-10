package com.ncu.car_manage.service;

import com.ncu.car_manage.pojo.Car;

import java.util.List;

public interface CarService {
    List<Car> findAllCar();

    List<Car> selectBySelective(String brand, String color, String date);
    List<Car> findCarPage(int page,int size);
    Car findCarById(int id);

    Car findCarById2(int id);
    boolean findCarExist(int id);
    boolean addCar(Car car);
    boolean updateCar(Car car);
    boolean deleteCar(int id);

    List<Car> findAll();
}

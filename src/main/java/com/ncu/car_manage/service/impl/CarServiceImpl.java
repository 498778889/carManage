package com.ncu.car_manage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ncu.car_manage.mapper.CarMapper;
import com.ncu.car_manage.pojo.Car;
import com.ncu.car_manage.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarMapper carMapper;

    @Override
    public List<Car> findAllCar() {
        return carMapper.findAllCar();
    }

    @Override
    public List<Car> findCarPage(int page, int size) {
        return carMapper.findCarPage(page,size);
    }

    @Override
    public Car findCarById(int id) {
        return carMapper.findCarById(id);
    }

    @Override
    public boolean findCarExist(int id) {
        return carMapper.findCarById(id)!=null;
    }

    @Override
    public boolean addCar(Car car) {
        return carMapper.addCar(car) >= 1;
    }

    @Override
    public boolean updateCar(Car car) {
        return carMapper.update(car) >= 1;
    }

    @Override
    public boolean deleteCar(int id) {
        return carMapper.delete(id) >= 1;
    }

    @Override
    public PageInfo<Car> findAll(int page, int size) {
        PageHelper.startPage(page, size);
        return new PageInfo<>(carMapper.findAllCar());
    }
}

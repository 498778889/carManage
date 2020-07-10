package com.ncu.car_manage.service.impl;

import com.ncu.car_manage.mapper.CarMapper;
import com.ncu.car_manage.pojo.Car;
import com.ncu.car_manage.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarMapper carMapper;

    @Override
    public List<Car> findAllCar() {
        return carMapper.findAllCar();
    }

    @Override
    public List<Car> selectBySelective(String brand, String color, String date) {
        return carMapper.selectBySelective(brand, color, date);
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
    public Car findCarById2(int id) {
        return carMapper.findCarById2(id);
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
    public List<Car> findAll() {
        return carMapper.findAllCar();
    }
}

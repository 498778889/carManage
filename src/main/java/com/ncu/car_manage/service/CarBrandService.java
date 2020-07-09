package com.ncu.car_manage.service;

import com.ncu.car_manage.pojo.CarBrand;

import java.math.BigDecimal;
import java.util.List;

public interface CarBrandService {
    boolean insert(CarBrand carBrand);
    boolean delete(BigDecimal id);
    List<CarBrand> findAllBrand();
    CarBrand findBrandById(BigDecimal id);
}

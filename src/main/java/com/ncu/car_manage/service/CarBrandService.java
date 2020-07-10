package com.ncu.car_manage.service;

import com.github.pagehelper.PageInfo;
import com.ncu.car_manage.pojo.CarBrand;

import java.math.BigDecimal;
import java.util.List;

public interface CarBrandService {
    boolean insert(CarBrand carBrand);
    boolean delete(BigDecimal id);
    PageInfo<CarBrand> findAllBrand(int page,int size);
    CarBrand findBrandById(BigDecimal id);
}

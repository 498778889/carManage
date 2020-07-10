package com.ncu.car_manage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ncu.car_manage.mapper.CarBrandmapper;
import com.ncu.car_manage.pojo.CarBrand;
import com.ncu.car_manage.service.CarBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CarBrandServiceImpl implements CarBrandService {
    @Autowired
    private CarBrandmapper carBrandmapper;

    @Override
    public boolean insert(CarBrand carBrand) {
        return carBrandmapper.insert(carBrand)>=1;
    }

    @Override
    public boolean delete(BigDecimal id) {
        return carBrandmapper.deleteByPrimaryKey(id)>=1;
    }

    @Override
    public PageInfo<CarBrand> findAllBrand(int page, int size) {
        PageHelper.startPage(page, size);
        return new PageInfo<>(carBrandmapper.selectAll());
    }

    @Override
    public CarBrand findBrandById(BigDecimal id) {
        return carBrandmapper.selectByPrimaryKey(id);
    }
}

package com.ncu.car_manage.service.impl;

import com.ncu.car_manage.mapper.CarBrandmapper;
import com.ncu.car_manage.pojo.CarBrand;
import com.ncu.car_manage.service.CarBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Transactional
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
    public List<CarBrand> findAllBrand() {
        return carBrandmapper.selectAll();
    }

    @Override
    public List<CarBrand> findBrandByName(String brand) {
        return carBrandmapper.selectByName(brand);
    }


    @Override
    public CarBrand findBrandById(BigDecimal id) {
        return carBrandmapper.selectByPrimaryKey(id);
    }
}

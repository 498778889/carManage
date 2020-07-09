package com.ncu.car_manage.service.impl;

import com.ncu.car_manage.mapper.CarRecordmapper;
import com.ncu.car_manage.pojo.CarRecord;
import com.ncu.car_manage.service.CarRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CarRecordServiceImpl implements CarRecordService {
    @Autowired
    private CarRecordmapper carRecordmapper;

    @Override
    public boolean insert(CarRecord record) {
        return carRecordmapper.insert(record) >= 1;
    }

    @Override
    public List<CarRecord> findAll() {
        return carRecordmapper.selectAll();
    }

    @Override
    public CarRecord findById(BigDecimal id) {
        return carRecordmapper.selectByPrimaryKey(id);
    }
}

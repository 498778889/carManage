package com.ncu.car_manage.service;

import com.ncu.car_manage.pojo.CarRecord;

import java.math.BigDecimal;
import java.util.List;

public interface CarRecordService {
    boolean insert(CarRecord record);
    List<CarRecord> findAll();
    CarRecord findById(BigDecimal id);
}

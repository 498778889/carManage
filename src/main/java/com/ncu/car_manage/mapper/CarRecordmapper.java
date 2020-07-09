package com.ncu.car_manage.mapper;

import com.ncu.car_manage.pojo.CarRecord;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface CarRecordmapper {
    int deleteByPrimaryKey(BigDecimal id);

    int insert(CarRecord record);

    int insertSelective(CarRecord record);

    CarRecord selectByPrimaryKey(BigDecimal id);

    List<CarRecord> selectAll();

    int updateByPrimaryKeySelective(CarRecord record);

    int updateByPrimaryKey(CarRecord record);
}
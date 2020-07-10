package com.ncu.car_manage.mapper;

import com.ncu.car_manage.pojo.CarBrand;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface CarBrandmapper {
    int deleteByPrimaryKey(BigDecimal id);

    int insert(CarBrand record);

    int insertSelective(CarBrand record);

    CarBrand selectByPrimaryKey(BigDecimal id);

    List<CarBrand> selectAll();

    int updateByPrimaryKeySelective(CarBrand record);

    List<CarBrand> selectByName(@Param("brand") String brand);

    int updateByPrimaryKey(CarBrand record);
}
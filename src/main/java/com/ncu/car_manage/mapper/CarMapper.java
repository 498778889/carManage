package com.ncu.car_manage.mapper;

import com.ncu.car_manage.pojo.Car;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CarMapper {
    List<Car> findAllCar();
    List<Car> findCarPage(@Param("page") int page, @Param("size")int size);
    Car findCarById(int id);

    Car findCarById2(int id);
    int addCar(Car car);
    int delete(int id);
    int update(Car car);

    List<Car> selectBySelective(@Param("brand") String brand, @Param("color") String color, @Param("date") String date);
}

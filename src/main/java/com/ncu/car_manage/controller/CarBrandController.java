package com.ncu.car_manage.controller;

import com.ncu.car_manage.pojo.CarBrand;
import com.ncu.car_manage.service.CarBrandService;
import com.ncu.car_manage.utils.JsonResult;
import com.ncu.car_manage.utils.LoggerOperater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/brand")
public class CarBrandController {
    @Autowired
    private CarBrandService carBrandService;

    @GetMapping("/addBrand")
    @LoggerOperater(type = "add")
    public JsonResult addBrand(String brand){
        CarBrand carBrand = new CarBrand();
        carBrand.setBrand(brand);
        if (carBrandService.insert(carBrand)){
            return JsonResult.OK("添加成功!");
        }
        return JsonResult.OK("添加失败!");
    }

    @GetMapping("/deleteBrand")
    @LoggerOperater(type = "delete")
    public JsonResult addBrand(int id){
        if (carBrandService.delete(BigDecimal.valueOf(id))){
            return JsonResult.OK("删除成功!");
        }
        return JsonResult.OK("删除失败!");
    }
}

package com.ncu.car_manage.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ncu.car_manage.pojo.CarBrand;
import com.ncu.car_manage.service.CarBrandService;
import com.ncu.car_manage.utils.JsonResult;
import com.ncu.car_manage.utils.LoggerOperater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

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

    @PostMapping("/findBrandPage")
    @ResponseBody
    public JsonResult findBrandPage(@RequestParam(required = false, defaultValue = "1") int page,
                                    @RequestParam(required = false, defaultValue = "4") int size,
                                    @RequestParam(required = false) String brand) {
        PageHelper.startPage(page, size);
        PageInfo<CarBrand> carList;
        if (brand == null) {
            carList = new PageInfo<>(carBrandService.findAllBrand());
        } else {
            carList = new PageInfo<>(carBrandService.findBrandByName(brand));
        }

        return JsonResult.OK(carList.getList(), carList.getTotal());
    }

    @GetMapping("/findAllBrand")
    public JsonResult findAllBrand() {
        List<CarBrand> list = carBrandService.findAllBrand();

        return JsonResult.OK(list);
    }

}

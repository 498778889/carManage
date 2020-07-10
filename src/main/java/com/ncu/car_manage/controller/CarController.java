package com.ncu.car_manage.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ncu.car_manage.pojo.Car;
import com.ncu.car_manage.service.CarService;
import com.ncu.car_manage.utils.JsonResult;
import com.ncu.car_manage.utils.LoggerOperater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/car")
public class CarController {
    @Autowired
    private CarService carService;

    @PostMapping("/add")
    @LoggerOperater(type = "add")
    public JsonResult addCar(@RequestBody Car car){
        if (carService.addCar(car)){
            return JsonResult.OK("添加成功!");
        }
        return JsonResult.ERROR("添加失败");
    }

    @PostMapping("/update")
    @LoggerOperater(type = "update")
    public JsonResult updateCar(@RequestBody Car car){
        if (carService.updateCar(car)){
            return JsonResult.OK("修改成功!");
        }
        return JsonResult.ERROR("修改失败");
    }

    @GetMapping("/delete")
    @LoggerOperater(type = "delete")
    public JsonResult deleteCar(int id){
        if (carService.deleteCar(id)){
            return JsonResult.OK("删除成功!");
        }
        return JsonResult.OK("删除失败!");
    }
    @LoggerOperater(type = "deleteSelected")
    @RequestMapping("/deleteSelected")
    public JsonResult deleteSelected(List<Integer> idList ){
        boolean action= false;
        for(int id:idList){
            if (carService.deleteCar(id)){
                action= true;
            }else {
                return JsonResult.OK("删除失败!");
            }
        }
        if (action){
            return JsonResult.OK("删除成功!");
        }else {
            return JsonResult.OK("删除失败!");
        }
    }

    @GetMapping("/findAll")
    public String findAllCar(Model model){
        List<Car> carList = carService.findAllCar();
        if (carList!=null){
            model.addAttribute(carList);
            return "findAll";
        }
        return "error";
    }
    @PostMapping("/findCarPage")
    @ResponseBody
    public JsonResult findCarPage(@RequestParam(required = false,defaultValue = "1")int page,
                                  @RequestParam(required = false, defaultValue = "4") int size,
                                  @RequestParam(required = false) String brand,
                                  @RequestParam(required = false) String color,
                                  @RequestParam(required = false) String date) {
        PageInfo<Car> carList;
        PageHelper.startPage(page, size);
        if (Objects.equals(brand, "")) {
            brand = null;
        }
        if (Objects.equals(color, "")) {
            color = null;
        }
        if (Objects.equals(date, "")) {
            date = null;
        }
        if ((brand != null) || (color != null) || (date != null)) {
            carList = new PageInfo<>(carService.selectBySelective(brand, color, date));
        } else {
            carList = new PageInfo<>(carService.findAll());
        }
        return JsonResult.OK(carList.getList(), carList.getTotal());
    }
    @GetMapping("/findCar")
    public JsonResult findCar(String id){
        Car car = carService.findCarById(Integer.parseInt(id));
        if (car!=null){
            return JsonResult.OK(car);
        }
        return JsonResult.ERROR("查询不到数据!");
    }

    @GetMapping("/findCar2")
    public JsonResult findCar2(String id) {
        Car car = carService.findCarById2(Integer.parseInt(id));
        if (car != null) {
            return JsonResult.OK(car);
        }
        return JsonResult.ERROR("查询不到数据!");
    }
}

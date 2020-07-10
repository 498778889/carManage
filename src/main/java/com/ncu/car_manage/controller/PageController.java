package com.ncu.car_manage.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ncu.car_manage.pojo.Car;
import com.ncu.car_manage.pojo.CarBrand;
import com.ncu.car_manage.pojo.CarRecord;
import com.ncu.car_manage.pojo.User;
import com.ncu.car_manage.service.CarBrandService;
import com.ncu.car_manage.service.CarRecordService;
import com.ncu.car_manage.service.CarService;
import com.ncu.car_manage.service.UserService;
import com.ncu.car_manage.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/carManage")
public class PageController {
    @Autowired
    private CarService carService;
    @Autowired
    private UserService userService;
    @Autowired
    private CarRecordService carRecordService;
    @RequestMapping(value = "/login", name = "登录")
    public String toLogin() {
        return "login";
    }

    @RequestMapping(value = "/register", name = "注册")
    public String toRegister() {
        return "register";
    }

    @RequestMapping(value = "/index", name = "主页")
    public String toIndex(Model model, String userName) {
        model.addAttribute("userName",userName);
        return "index";
    }
    @RequestMapping(value = "/userDetail", name = "用户信息")
    public String toUserDetail(Model model,String userName){
        User exist=userService.findUserByUserName(userName);
        model.addAttribute("user",exist);
        model.addAttribute("userName",userName);
        return "userDetail";
    }

    @RequestMapping(value = "/carDetail/{id}", name = "详细信息")
    public String toCarDetail(Model model, @PathVariable String id,String userName) {
        Car car = carService.findCarById(Integer.parseInt(id));
        model.addAttribute("car",car);
        model.addAttribute("userName",userName);
        return "carDetail";
    }

    @RequestMapping(value = "/brandDetail", name = "品牌详细信息")
    public String toBrandDetail(Model model,String userName) {
        model.addAttribute("userName",userName);
        return "carBrandManage";
    }

    @RequestMapping(value = "/logDetail", name = "日志详细信息")
    public String toLogDetail(Model model,String userName) {
        model.addAttribute("userName",userName);
        return "logDetail";
    }

    @PostMapping("/findRecodPage")
    @ResponseBody
    public JsonResult findRecodPage(@RequestParam(required = false,defaultValue = "1")int page,
                                  @RequestParam(required = false,defaultValue = "4")int size){
        PageHelper.startPage(page, size);
        PageInfo<CarRecord> carList = new PageInfo<>(carRecordService.findAll());

        if (carList!=null){
            return JsonResult.OK(carList.getList(),carList.getTotal());
        }
        return JsonResult.ERROR("查询不到数据!");
    }
}

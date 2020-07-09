package com.ncu.car_manage.controller;

import com.github.pagehelper.PageInfo;
import com.ncu.car_manage.pojo.Car;
import com.ncu.car_manage.pojo.User;
import com.ncu.car_manage.service.CarService;
import com.ncu.car_manage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/carManage")
public class PageController {
    @Autowired
    private CarService carService;
    @Autowired
    private UserService userService;
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
}

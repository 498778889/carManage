package com.ncu.car_manage.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.ncu.car_manage.pojo.Car;
import com.ncu.car_manage.pojo.CarRecord;
import com.ncu.car_manage.pojo.User;
import com.ncu.car_manage.service.CarRecordService;
import com.ncu.car_manage.service.CarService;
import com.ncu.car_manage.service.UserService;
import com.ncu.car_manage.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.*;

@Controller
@RequestMapping("/carManage")
public class PageController {
    @Autowired
    private CarService carService;
    @Autowired
    private UserService userService;
    @Autowired
    private CarRecordService carRecordService;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @RequestMapping(value = "/login", name = "登录")
    public String toLogin() {
        return "login";
    }

    @RequestMapping(value = "/logout", name = "注销")
    public String Logout(HttpServletRequest request) {
        String redisKey = getCookie(request,"redisKey");
        redisTemplate.delete(redisKey);
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

    @PostMapping("/findRecordPage")
    @ResponseBody
    public JsonResult findRecodPage(@RequestParam(required = false,defaultValue = "1")int page,
                                  @RequestParam(required = false,defaultValue = "4")int size){
        PageHelper.startPage(page, size);
        PageInfo<CarRecord> carList = new PageInfo<>(carRecordService.findAll());

        return JsonResult.OK(carList.getList(), carList.getTotal());
    }

    public static String getCookie(HttpServletRequest request, String key){
        if(request == null || StringUtil.isEmpty(key)){
            return "";
        }
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if(key.equals(cookie.getName())){
                return cookie.getValue();
            }
        }
        return "";
    }
}

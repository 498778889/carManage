package com.ncu.car_manage.controller;

import com.ncu.car_manage.pojo.User;
import com.ncu.car_manage.service.UserService;
import com.ncu.car_manage.utils.JsonResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/User")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public JsonResult register(@RequestBody User user){
        boolean res = userService.register(user);
        if (res){
            return JsonResult.OK("success");
        }
        return JsonResult.ERROR("用户名已经存在!请更换!");
    }
    @GetMapping("/exist/{userName}")
    public JsonResult exist(@PathVariable("userName") String userName){
        boolean exist=userService.findUserByName(userName);
        if (exist){
            return JsonResult.ERROR("该用户名已被注册!");
        }
        return JsonResult.OK("该用户名可以使用!");
    }

    @GetMapping("/find/{userName}")
    public JsonResult findUser(@PathVariable("userName") String userName){
        User exist=userService.findUserByUserName(userName);
        if (exist!=null){
            return JsonResult.OK(exist,1);
        }
        return JsonResult.ERROR("无法查询！");
    }

    @PostMapping("/login")
    public JsonResult login(@RequestBody User user){
        boolean exist=userService.findUserByName(user.getUserName());
        if (exist){
            if (userService.login(user.getUserName(), user.getPassword())){
                return JsonResult.OK("登录成功!");
            }
            return JsonResult.ERROR("密码错误!");
        }
        return JsonResult.ERROR("该用户不存在!");
    }
}

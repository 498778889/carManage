package com.ncu.car_manage.utils;

import com.ncu.car_manage.pojo.CarRecord;
import com.ncu.car_manage.service.CarRecordService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Objects;

@Aspect
@Component
public class LoggerAspect {

    @Autowired
    private CarRecordService carRecordService;

    @Pointcut("@annotation(com.ncu.car_manage.utils.LoggerOperater)")
    public void operLogPoinCut() {

    }

    @AfterReturning(value = "operLogPoinCut()", returning = "key")
    public void saveOperLog(JoinPoint joinPoint, Object key) {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))
                    .getRequest();
            // 从切面织入点处通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            // 获取切入点所在的方法
            Method method = signature.getMethod();
            // 获取操作
            LoggerOperater loggerOperater = method.getAnnotation(LoggerOperater.class);

            String type =loggerOperater.type();
            String str = request.getQueryString();
            String[] strS = str.split("=");
            String userName=strS[strS.length-1];
            BigDecimal id= BigDecimal.valueOf(0);;
            switch (type) {
                case "add":
                    id = BigDecimal.valueOf(1);
                    break;
                case "update":
                    id = BigDecimal.valueOf(2);
                    break;
                case "delete":
                    id = BigDecimal.valueOf(3);
                    break;
                case "register":
                    id = BigDecimal.valueOf(4);
                    break;
                case "deleteSelected":
                    id = BigDecimal.valueOf(5);
                    break;
            }

            CarRecord carRecord = new CarRecord();
            carRecord.setUsername(userName);
            carRecord.setOperationType(id);
            carRecordService.insert(carRecord);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description 获取注解中对方法的描述信息 用于Controller层注解
     */
    private static String getControllerMethodDescription(JoinPoint joinPoint) throws ClassNotFoundException {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();// 目标方法名
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String type = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    type = method.getAnnotation(LoggerOperater.class).type();
                    break;
                }
            }
        }
        return type;
    }

}

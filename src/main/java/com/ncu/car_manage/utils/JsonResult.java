package com.ncu.car_manage.utils;

import lombok.Data;

@Data
/**
 * 前台请求后台,后台返回给前台的封装JSON数据对象
 */
public class JsonResult {
    // 状态码 200 正常, 500错误..
    private Integer status;
    private long count;
    // 返回的数据
    private Object data;
    public static JsonResult OK(Object data) {
        JsonResult result = new JsonResult();
        result.data = data;
        result.status = 200;
        return result;
    }
    public static JsonResult OK(Object data,long count) {
        JsonResult result = new JsonResult();
        result.count = count;
        result.data = data;
        result.status = 0;
        return result;
    }
    public static JsonResult ERROR(Object data) {
        JsonResult result = new JsonResult();
        result.data = data;
        result.status = 500;
        return result;
    }
}

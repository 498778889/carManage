package com.ncu.car_manage.controller;

import com.ncu.car_manage.utils.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/file")
public class FileController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class) ;
    /**上传地址*/
    @Value("${file.upload.path}")
    private String filePath;
    // 执行上传
    @RequestMapping("/upload")
    public JsonResult upload(@RequestParam("file") MultipartFile file) {
        // 定义上传文件保存路径
        String path = filePath+"rotPhoto/";
        // 新建文件
        int lastIndexOf = file.getOriginalFilename().lastIndexOf(".");
        String suffix = file.getOriginalFilename().substring(lastIndexOf);
        String filename = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase()+suffix;

        File filepath = new File(path, filename);

        LOGGER.info("Name == >>{}",file.getName());
        LOGGER.info("OriginalFilename == >>{}",file.getOriginalFilename());
        LOGGER.info("ContentType == >>{}",file.getContentType());
        LOGGER.info("Size == >>{}",file.getSize());

        // 判断路径是否存在，如果不存在就创建一个
        if (!filepath.getParentFile().exists()) {
            filepath.getParentFile().mkdirs();
        }
        try {
            // 写入文件
            file.transferTo(new File(path + File.separator + filename));
        } catch (IOException e) {
            e.printStackTrace();
            return JsonResult.ERROR("上传失败!");
        }
        // 将src路径发送至html页面
        filename = "/images/rotPhoto/"+filename;
        return JsonResult.OK(filename);
    }
}

package com.example.demo.domain;

import base.JsonResult;
import base.ResultUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

/**
 * @ClassName FileUploadController
 * @Description ass we see
 * @Author sun_liber
 * @Date 2019/12/18
 * @Version 1.0.0
 */
@RestController
@RequestMapping ("/upload")
public class FileUploadController {

    @Value ("${web.upload-path}")
    String path;

    @RequestMapping (value = "/img", method = RequestMethod.POST)
    JsonResult addImg(@RequestParam ("file") MultipartFile file) throws IOException {
        String key= UUID.randomUUID().toString();
        Files.copy(file.getInputStream(), Paths.get(path,key), StandardCopyOption.REPLACE_EXISTING);
        return ResultUtils.success(key);
    }
}

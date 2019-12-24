package com.example.demo.domain.rpc;


import base.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName DataController
 * @Description todo
 * @Author sun_liber
 * @Date 2019/11/20
 * @Version 1.0.0
 */
@RestController
@RequestMapping (value = "/dc")
public class DataController {

    @Autowired
    DCClient dcClient;

    @RequestMapping (value = "/dataitem/addByStorage", method = RequestMethod.POST)
    public JsonResult addWithStorage(@RequestParam ("file") MultipartFile file) {
        return dcClient.addWithStorage(file);
    }

    @RequestMapping (value = "/dataitem/{id}/download", method = RequestMethod.GET)
    public ResponseEntity<Resource> downloadByDataItemId(@PathVariable ("id") String dataItemId) {
        return dcClient.downloadByDataItemId(dataItemId);
    }
}

package com.example.demo.zaqizaba;


import base.JsonResult;
import base.ResultUtils;
import com.example.demo.domain.resource.Resource;
import com.example.demo.domain.resource.ResourceRepository;
import com.example.demo.domain.resource.support.ActivityEnum;
import com.example.demo.domain.rpc.DCClient;
import com.example.demo.domain.rpc.MCClient;
import edu.njnu.opengms.common.utils.JavaBeanCollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName TestController
 * @Description todo
 * @Author sun_liber
 * @Date 2019/10/10
 * @Version 1.0.0
 */
@RestController
@RequestMapping ("/test")
public class TestController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    DCClient dcClient;

    @Autowired
    MCClient mcClient;


    @Autowired
    ResourceRepository resourceRepository;

    @Autowired
    MongoTemplate mongoTemplate;


    @RequestMapping (value = "/JavaBeanCollectionUtils", method = RequestMethod.GET)
    public JsonResult JavaBeanCollectionUtilsT() {
        List<Resource> books=new ArrayList<>();
        books.add(new Resource());
        books.add(new Resource());
        books.add(new Resource());
        Map<String, String> stringStringMap = JavaBeanCollectionUtils.convertToMap(books, Resource::getId, Resource::getName);
        return ResultUtils.success(Math.random());
    }

    @RequestMapping (value = "/test1", method = RequestMethod.GET)
    public JsonResult test1() {
      Map<String, ActivityEnum> map=new HashMap<>();
        map.put("1",ActivityEnum.SIMULATION);
        map.put("2",ActivityEnum.SIMULATION);
        map.forEach((key,value)->{
            System.out.println(key+":"+value);
        });
        return ResultUtils.success("");
    }


}

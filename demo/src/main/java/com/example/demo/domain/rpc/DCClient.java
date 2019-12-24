package com.example.demo.domain.rpc;

import base.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;


/**
 * @InterfaceName DCClient
 * @Description 当未使用EurekaClient的时候，需要指定name和url,注意此时name可以随便命名
 * 当使用EurekaClient时 可以使用name，从注册中心获取，不用指定url
 * 当使用继承时，接口不能带有泛型，只能有一个父接口（单层继承），使用继承会将两个系统耦合得过分精密,同时客户端能够完全访问服务端的接口，无法做接口的屏蔽功能。
 * 另外服务提供商往往并不止一个Controller这样，往往需要多个DDServe，因此我选用的方式，是自己在DCServe中通过声明式的方式来构建。
 * 若存在别的服务提供商，只是提供了BaseController的服务那么可以使用extends BaseController + path的方法直接继承
 * @Author sun_liber
 * @Date 2019/11/20
 * @Version 1.0.0
 */
@FeignClient (name = "dc-client",
        url = "http://127.0.0.1:8081",
        fallback = DCClient.FeignServiceFallBack.class)
public interface DCClient {

    @RequestMapping (value = "/dataitem/addByStorage", method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    JsonResult addWithStorage(@RequestPart ("file") MultipartFile file);

    @RequestMapping (value = "/storage/downloadByDataItemId/{dataItemId}", method = RequestMethod.GET)
    ResponseEntity<Resource> downloadByDataItemId(@PathVariable ("dataItemId") String dataItemId);

    @Component
    class FeignServiceFallBack implements DCClient {

        @Override
        public JsonResult addWithStorage(MultipartFile file) {
            //TODO
            return null;
        }

        @Override
        public ResponseEntity<Resource> downloadByDataItemId(String dataItemId) {
            //TODO
            return null;
        }
    }

}

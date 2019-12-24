package com.example.demo.domain.rpc;

import base.JsonResult;
import domain.modelinstance.dto.AddModelInstanceDTO;
import domain.modelinstance.dto.UpdateModelInstanceDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;


/**
 * @InterfaceName MCClientModelInstance
 * @Description todo
 * @Author sun_liber
 * @Date 2019/11/20
 * @Version 1.0.0
 */
@FeignClient (name = "mc-client",
        url = "http://127.0.0.1:8082",
        fallback = MCClient.FeignServiceFallBack.class)
public interface MCClient {

    @RequestMapping (value = "/modelitem/{id}", method = RequestMethod.GET)
    JsonResult getModelItem(@PathVariable (value = "id") String id);

    @RequestMapping (value = "/modelitem", method = RequestMethod.GET)
    JsonResult getModelItems(@RequestParam Integer page, @RequestParam  Integer pageSize,@RequestParam Boolean asc);

    @RequestMapping (value = "/modelinstance/{id}", method = RequestMethod.GET)
    JsonResult getModelInstance(@PathVariable (value = "id") String id);

    @RequestMapping (value = "/modelinstance", method = RequestMethod.POST)
    JsonResult addModelInstance(@RequestBody AddModelInstanceDTO addModelInstanceDTO);

    @RequestMapping (value = "/modelinstance/{id}/invoke", method = RequestMethod.POST)
    JsonResult invokeModelInstance(@PathVariable (value = "id") String id);

    @RequestMapping (value = "/modelinstance/{id}", method = RequestMethod.PUT)
    JsonResult updateModelInstance(@PathVariable ("id") String id, @RequestBody UpdateModelInstanceDTO updateModelInstanceDTO);




    @Component
    class FeignServiceFallBack implements MCClient {


        @Override
        public JsonResult getModelItem(String id) {
            return null;
        }

        @Override
        public JsonResult getModelItems(Integer page, Integer pageSize, Boolean asc) {
            return null;
        }


        @Override
        public JsonResult getModelInstance(String id) {
            return null;
        }

        @Override
        public JsonResult addModelInstance(AddModelInstanceDTO addModelInstanceDTO) {
            return null;
        }

        @Override
        public JsonResult invokeModelInstance(String id) {
            return null;
        }

        @Override
        public JsonResult updateModelInstance(String id, UpdateModelInstanceDTO updateModelInstanceDTO) {
            return null;
        }
    }
}

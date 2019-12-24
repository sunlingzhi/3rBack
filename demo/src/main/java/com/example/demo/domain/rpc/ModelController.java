package com.example.demo.domain.rpc;

import base.JsonResult;
import base.dto.SplitPageDTO;
import domain.modelinstance.dto.AddModelInstanceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName ModelController
 * @Description todo
 * @Author sun_liber
 * @Date 2019/11/20
 * @Version 1.0.0
 */
@RestController
@RequestMapping (value = "/mc")
public class ModelController {

    @Autowired
    MCClient mcClient;

    @RequestMapping (value = "/modelitem", method = RequestMethod.GET)
    JsonResult getModelItems(SplitPageDTO splitPageDTO){
        return mcClient.getModelItems(splitPageDTO.getPage(),splitPageDTO.getPageSize(),splitPageDTO.getAsc());
    }

    @RequestMapping (value = "/modelitem/{id}", method = RequestMethod.GET)
    JsonResult getModelItem(@PathVariable (value = "id") String id){
        return mcClient.getModelItem(id);
    }

    @RequestMapping (value = "/modelinstance/{id}", method = RequestMethod.GET)
    JsonResult getModelInstance(@PathVariable ("id") String id) {
        return mcClient.getModelInstance(id);
    }

    @RequestMapping (value = "/modelinstance", method = RequestMethod.POST)
    JsonResult addModelInstance(@RequestBody AddModelInstanceDTO addModelInstanceDTO){
        return mcClient.addModelInstance(addModelInstanceDTO);
    }

    @RequestMapping (value = "/modelinstance/{id}/invoke", method = RequestMethod.POST)
    JsonResult invokeModelInstance(@PathVariable ("id") String id) {
        return mcClient.invokeModelInstance(id);
    }
}

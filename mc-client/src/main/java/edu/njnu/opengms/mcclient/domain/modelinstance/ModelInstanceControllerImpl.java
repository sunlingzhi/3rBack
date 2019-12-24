package edu.njnu.opengms.mcclient.domain.modelinstance;


import base.JsonResult;
import base.ResultUtils;
import domain.modelinstance.ModelInstanceController;
import domain.modelinstance.dto.AddModelInstanceDTO;
import domain.modelinstance.dto.UpdateModelInstanceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ModelInstanceController
 * @Description todo
 * @Author sun_liber
 * @Date 2019/11/7
 * @Version 1.0.0
 */
@RestController

public class ModelInstanceControllerImpl implements ModelInstanceController {

    @Autowired
    ModelInstanceService modelInstanceService;




    @Override
    public JsonResult get(String id) {
        return ResultUtils.success(modelInstanceService.get(id));
    }

    @Override
    public JsonResult add(AddModelInstanceDTO addModelInstanceDTO) {
        return ResultUtils.success(modelInstanceService.add(addModelInstanceDTO));
    }


    @Override
    public JsonResult invoke(String id) {
        return ResultUtils.success(modelInstanceService.invoke(id));
    }

    @Override
    public JsonResult update(String id, UpdateModelInstanceDTO updateModelInstanceDTO) {
        return ResultUtils.success(modelInstanceService.update(id,updateModelInstanceDTO));
    }

}

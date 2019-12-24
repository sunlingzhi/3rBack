package edu.njnu.opengms.mcclient.domain.modelitem;

import base.JsonResult;
import base.ResultUtils;
import domain.modelitem.ModelItemController;
import domain.modelitem.dto.AddModelItemDTO;
import domain.modelitem.dto.FindModelItemDTO;
import domain.modelitem.dto.UpdateModelItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ModelItemControllerImpl
 * @Description todo
 * @Author sun_liber
 * @Date 2019/11/22
 * @Version 1.0.0
 */
@RestController
public class ModelItemControllerImpl implements ModelItemController<AddModelItemDTO, FindModelItemDTO, UpdateModelItemDTO> {

    @Autowired
    ModelItemService modelItemService;


    @Override
    public JsonResult add(AddModelItemDTO a) {
        return ResultUtils.success(modelItemService.add(a));
    }


    @Override
    public JsonResult get(String s) {
        return ResultUtils.success(modelItemService.get(s));
    }

    @Override
    public JsonResult list(FindModelItemDTO findDTO) {
        return ResultUtils.success(modelItemService.list(findDTO));
    }



    @Override
    public JsonResult count() {
        return ResultUtils.success(modelItemService.count());
    }

    @Override
    public JsonResult update(String s, UpdateModelItemDTO updateDTO) {
        return ResultUtils.success(modelItemService.update(s,updateDTO));
    }

    @Override
    public JsonResult delete(String s) {
        modelItemService.delete(s);
        return ResultUtils.success("删除成功");
    }

}

package edu.njnu.opengms.dcclient.domain.dataitem;


import base.JsonResult;
import base.ResultUtils;
import domain.dataitem.DataItemController;
import domain.dataitem.dto.AddDataItemDTO;
import domain.dataitem.dto.FindDataItemDTO;
import domain.dataitem.dto.UpdateDataItemDTO;
import domain.store.FileItemStorage;
import edu.njnu.opengms.dcclient.domain.store.FileItemStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @ClassName DataItemControllerImpl
 * @Description todo
 * @Author sun_liber
 * @Date 2019/11/22
 * @Version 1.0.0
 */
@RestController
public class DataItemControllerImpl implements DataItemController<AddDataItemDTO,FindDataItemDTO, UpdateDataItemDTO> {

    @Autowired
    FileItemStorageService fileItemStorageService;

    @Autowired
    DataItemService dataItemService;

    @Override
    public JsonResult addWithStorage(MultipartFile file) throws IOException {
        FileItemStorage fileItemStorage = fileItemStorageService.store(file.getInputStream(), file.getSize(), file.getContentType(), file.getOriginalFilename());
        AddDataItemDTO addDataItemDTO=AddDataItemDTO.builder().name(fileItemStorage.getName()).storeKey(fileItemStorage.getKey()).build();
        return ResultUtils.success( dataItemService.add(addDataItemDTO));
    }


    @Override
    public JsonResult add(AddDataItemDTO a) {
        return ResultUtils.success(dataItemService.add(a));
    }

    @Override
    public JsonResult delete(String s) {
        dataItemService.delete(s);
        return ResultUtils.success("删除成功");
    }

    @Override
    public JsonResult list(FindDataItemDTO findDTO) {
        return ResultUtils.success(dataItemService.list(findDTO));
    }

    @Override
    public JsonResult count() {
        return ResultUtils.success(dataItemService.count());
    }

    @Override
    public JsonResult get(String id) {
        return ResultUtils.success(dataItemService.get(id));
    }

    @Override
    public JsonResult update(String id, UpdateDataItemDTO updateDTO) {
        return ResultUtils.success(dataItemService.update(id,updateDTO));
    }
}

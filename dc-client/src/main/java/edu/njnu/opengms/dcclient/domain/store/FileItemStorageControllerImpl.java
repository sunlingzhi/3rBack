package edu.njnu.opengms.dcclient.domain.store;

import base.JsonResult;
import base.ResultUtils;
import domain.dataitem.DataItem;
import domain.store.FileItemStorage;
import domain.store.FileItemStorageController;
import edu.njnu.opengms.dcclient.domain.dataitem.DataItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @ClassName FileItemStorageControllerImpl
 * @Description todo
 * @Author sun_liber
 * @Date 2019/11/22
 * @Version 1.0.0
 */
@RestController
public class FileItemStorageControllerImpl implements FileItemStorageController {

    @Autowired
    FileItemStorageService fileItemStorageService;

    @Autowired
    DataItemService dataItemService;

    @Override
    public JsonResult upload(MultipartFile file) throws IOException {
        FileItemStorage fileItemStorage = fileItemStorageService.store(file.getInputStream(), file.getSize(), file.getContentType(), file.getOriginalFilename());
        return ResultUtils.success(fileItemStorage);
    }

    @Override
    public JsonResult get(String key) {
        FileItemStorage byKey = fileItemStorageService.findByKey(key);
        return ResultUtils.success(byKey);
    }

    @Override
    public ResponseEntity<Resource> fetch(String key) {
        FileItemStorage byKey = fileItemStorageService.findByKey(key);
        MediaType mediaType = MediaType.parseMediaType(byKey.getContentType());
        return ResponseEntity.ok().contentType(mediaType).body(fileItemStorageService.loadAsResource(key));
    }

    @Override
    public ResponseEntity<Resource> downloadByStoreKey(String storeKey) {
        FileItemStorage byKey = fileItemStorageService.findByKey(storeKey);
        MediaType mediaType = MediaType.parseMediaType(byKey.getContentType());
        return ResponseEntity
                .ok()
                .contentType(mediaType)
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=\""+byKey.getName())
                .body(fileItemStorageService.loadAsResource(storeKey));
    }

    @Override
    public ResponseEntity<Resource> downloadByDataItemId(String dataItemId) {
        DataItem dataItem = dataItemService.get(dataItemId);;
        FileItemStorage byKey = fileItemStorageService.findByKey(dataItem.getStoreKey());
        MediaType mediaType = MediaType.parseMediaType(byKey.getContentType());
        return ResponseEntity
                .ok()
                .contentType(mediaType)
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=\""+byKey.getName())
                .body(fileItemStorageService.loadAsResource(dataItem.getStoreKey()));
    }
}

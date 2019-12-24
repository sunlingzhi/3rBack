package domain.store;

import base.JsonResult;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @InterfaceName FileItemStorageController
 * @Description todo
 * @Author sun_liber
 * @Date 2019/11/22
 * @Version 1.0.0
 */
@RequestMapping("/storage")
public interface FileItemStorageController {
    @RequestMapping (value = "/upload",method = RequestMethod.POST)
    JsonResult upload(@RequestParam ("file") MultipartFile file) throws IOException;


    @RequestMapping (value = "/get/{key}",method = RequestMethod.GET)
    JsonResult get(@PathVariable ("key") String   key);


    @RequestMapping (value = "/fetch/{key}",method = RequestMethod.GET)
    ResponseEntity<Resource> fetch(@PathVariable ("key") String   key);


    @RequestMapping (value = "/downloadByStoreKey/{storeKey}",method = RequestMethod.GET)
    ResponseEntity<Resource> downloadByStoreKey(@PathVariable ("storeKey") String   storeKey);


    @RequestMapping (value = "/downloadByDataItemId/{dataItemId}",method = RequestMethod.GET)
    ResponseEntity<Resource> downloadByDataItemId(@PathVariable ("dataItemId") String   dataItemId);
}

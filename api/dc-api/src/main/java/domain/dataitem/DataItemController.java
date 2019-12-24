package domain.dataitem;


import base.JsonResult;
import base.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @InterfaceName DataItemController
 * @Description todo
 * @Author sun_liber
 * @Date 2019/11/22
 * @Version 1.0.0
 */
@RequestMapping ("/dataitem")
public interface DataItemController<AD,FD,UD> extends BaseController<AD,FD,UD> {
    @RequestMapping(value = "/addByStorage",method = RequestMethod.POST)
    JsonResult addWithStorage(@RequestParam ("file") MultipartFile file) throws IOException;
}

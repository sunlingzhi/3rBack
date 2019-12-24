package domain.modelinstance;

import base.JsonResult;
import domain.modelinstance.dto.AddModelInstanceDTO;
import domain.modelinstance.dto.UpdateModelInstanceDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @InterfaceName ModelInstanceController
 * @Description todo
 * @Author sun_liber
 * @Date 2019/11/22
 * @Version 1.0.0
 */
@RequestMapping ("/modelinstance")
public interface ModelInstanceController {

    @RequestMapping (value = "/{id}", method = RequestMethod.GET)
    JsonResult get(@PathVariable ("id") String id);

    @RequestMapping (value = "", method = RequestMethod.POST)
    JsonResult add(@RequestBody AddModelInstanceDTO addModelInstanceDTO);

    @RequestMapping (value = "/{id}/invoke", method = RequestMethod.POST)
    JsonResult invoke(@PathVariable ("id") String id);

    @RequestMapping (value = "/{id}", method = RequestMethod.PUT)
    JsonResult update(@PathVariable ("id") String id, @RequestBody UpdateModelInstanceDTO updateModelInstanceDTO);


}

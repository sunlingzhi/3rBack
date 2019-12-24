package domain.modelitem;

import base.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @InterfaceName ModelItemController
 * @Description todo
 * @Author sun_liber
 * @Date 2019/11/22
 * @Version 1.0.0
 */
@RequestMapping ("/modelitem")
public interface ModelItemController<AD,FD,UD> extends BaseController<AD,FD,UD> {
}

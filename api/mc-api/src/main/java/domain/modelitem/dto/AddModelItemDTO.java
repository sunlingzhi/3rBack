package domain.modelitem.dto;


import base.dto.ToDomainConverter;
import domain.modelitem.ModelItem;
import domain.modelitem.support.State;
import lombok.Data;

import java.util.List;

/**
 * @ClassName AddModelItemDTO
 * @Description todo
 * @Author sun_liber
 * @Date 2019/7/9
 * @Version 1.0.0
 */
@Data
public class AddModelItemDTO implements ToDomainConverter<ModelItem> {
    private String name;
    private String des;
    private List<State> stateList;
}

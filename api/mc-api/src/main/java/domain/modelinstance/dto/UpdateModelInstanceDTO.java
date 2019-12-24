package domain.modelinstance.dto;

import base.dto.ToDomainConverter;
import domain.modelinstance.ModelInstance;
import domain.modelinstance.support.StatusEnum;
import domain.modelitem.ModelItem;
import lombok.Builder;
import lombok.Data;

/**
 * @ClassName UpdateModelInstanceDTO
 * @Description todo
 * @Author sun_liber
 * @Date 2019/11/22
 * @Version 1.0.0
 */
@Data
public class UpdateModelInstanceDTO implements ToDomainConverter<ModelInstance> {
    private StatusEnum statusEnum;
    private ModelItem modelItem;
}

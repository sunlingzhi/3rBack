package domain.modelinstance.dto;


import base.dto.ToDomainConverter;
import domain.modelinstance.ModelInstance;
import domain.modelitem.ModelItem;
import lombok.Data;

/**
 * @ClassName AddModelInstanceDTO
 * @Description name 由memberName +_+ modelName组成
 * @Author sun_liber
 * @Date 2019/11/12
 * @Version 1.0.0
 */
@Data
public class AddModelInstanceDTO implements ToDomainConverter<ModelInstance> {
    private String name;
    private ModelItem modelItem;
}

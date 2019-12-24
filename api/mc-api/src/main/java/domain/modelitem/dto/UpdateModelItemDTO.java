package domain.modelitem.dto;


import base.dto.ToDomainConverter;
import domain.modelitem.ModelItem;
import lombok.Data;

/**
 * @ClassName UpdateModelItemDTO
 * @Description todo
 * @Author sun_liber
 * @Date 2019/7/9
 * @Version 1.0.0
 */
@Data
public class UpdateModelItemDTO implements ToDomainConverter<ModelItem> {
    String name;
}

package domain.dataitem.dto;

import base.dto.ToDomainConverter;
import domain.dataitem.DataItem;
import lombok.Data;

/**
 * @ClassName UpdateDataItemDTO
 * @Description todo
 * @Author sun_liber
 * @Date 2019/7/9
 * @Version 1.0.0
 */
@Data
public class UpdateDataItemDTO implements ToDomainConverter<DataItem> {
    String name;
}

package domain.dataitem.dto;

import base.dto.ToDomainConverter;
import domain.dataitem.DataItem;
import lombok.Builder;
import lombok.Data;


/**
 * @ClassName AddDataItemDTO
 * @Description todo
 * @Author sun_liber
 * @Date 2019/7/9
 * @Version 1.0.0
 */
@Data
@Builder
public class AddDataItemDTO implements ToDomainConverter<DataItem> {
    String name;
    String storeKey;
}

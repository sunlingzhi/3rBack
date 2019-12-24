package domain.dataitem;


import base.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @ClassName DataItem
 * @Description todo
 * @Author sun_liber
 * @Date 2019/7/5
 * @Version 1.0.0
 */
@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataItem extends BaseEntity {
    String id;
    String name;
    String storeKey;
}

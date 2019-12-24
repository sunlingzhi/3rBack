package domain.modelitem;


import base.entity.BaseEntity;
import domain.modelitem.support.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @ClassName ModelItem
 * @Description todo
 * @Author sun_liber
 * @Date 2019/7/5
 * @Version 1.0.0
 */
@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModelItem extends BaseEntity {
    @Id
    private String id;
    private String name;
    private String des;
    private List<State> stateList;
}

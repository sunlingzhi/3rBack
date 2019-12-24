package domain.modelinstance;


import base.entity.BaseEntity;
import domain.modelinstance.support.StatusEnum;
import domain.modelitem.ModelItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @ClassName ModelInstance
 * @Description todo
 * @Author sun_liber
 * @Date 2019/11/7
 * @Version 1.0.0
 */
@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModelInstance extends BaseEntity {
    @Id
    private String id;
    private String name;
    private StatusEnum statusEnum;
    private ModelItem modelItem;
}

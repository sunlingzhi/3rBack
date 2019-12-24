package domain.store;


import base.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @ClassName FileItemStorage
 * @Description todo
 * @Author sun_liber
 * @Date 2019/7/11
 * @Version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class FileItemStorage extends BaseEntity {
    @Id
    String id;
    String key;
    String name;
    String contentType;
    Long size;
    String url;
}

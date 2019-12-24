package edu.njnu.opengms.mcclient.domain.modelitem;

import domain.modelitem.ModelItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @InterfaceName BookRepository
 * @Description todo
 * @Author sun_liber
 * @Date 2019/7/5
 * @Version 1.0.0
 */
@Repository
public interface ModelItemRepository extends MongoRepository<ModelItem,String> {

}

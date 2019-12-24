package edu.njnu.opengms.dcclient.domain.dataitem;


import domain.dataitem.DataItem;
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
public interface DataItemRepository extends MongoRepository<DataItem,String> {
}

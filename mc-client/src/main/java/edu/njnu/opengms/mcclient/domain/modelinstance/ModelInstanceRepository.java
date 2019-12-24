package edu.njnu.opengms.mcclient.domain.modelinstance;

import domain.modelinstance.ModelInstance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @InterfaceName ModelInstanceRepository
 * @Description todo
 * @Author sun_liber
 * @Date 2019/11/7
 * @Version 1.0.0
 */
@Repository
public interface ModelInstanceRepository extends MongoRepository<ModelInstance,String> {
}

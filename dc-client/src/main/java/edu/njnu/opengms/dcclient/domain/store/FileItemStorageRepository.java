package edu.njnu.opengms.dcclient.domain.store;

import domain.store.FileItemStorage;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * @InterfaceName FileItemStorageRepository
 * @Description todo
 * @Author sun_liber
 * @Date 2019/7/11
 * @Version 1.0.0
 */
@Document
public interface FileItemStorageRepository extends MongoRepository<FileItemStorage,String> {
    Optional<FileItemStorage> findByKey(String key);
}

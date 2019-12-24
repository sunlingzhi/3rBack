package com.example.demo.domain.group;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @InterfaceName GroupRepository
 * @Description todo
 * @Author sun_liber
 * @Date 2019/11/27
 * @Version 1.0.0
 */
@Repository
public interface GroupRepository extends MongoRepository<Group, String> {
}

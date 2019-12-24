package com.example.demo.domain.resource.support.comment;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @InterfaceName CommentRepository
 * @Description todo
 * @Author sun_liber
 * @Date 2019/12/4
 * @Version 1.0.0
 */
@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {
}

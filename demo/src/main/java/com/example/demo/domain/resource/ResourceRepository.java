package com.example.demo.domain.resource;

import com.example.demo.domain.resource.support.CategoryEnum;
import com.example.demo.domain.resource.vo.ResourceListVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @InterfaceName BookRepository
 * @Description todo
 * @Author sun_liber
 * @Date 2019/11/27
 * @Version 1.0.0
 */
@Repository
public interface ResourceRepository extends MongoRepository<Resource, String> {
    Page<Resource> findByNameContains(String name,Pageable pageable);

    List<ResourceListVO> findDistinctByNameContainsAndMemberNameAndType(String name,String memberName,String type);

    Page<Resource> findByMemberName(String memberName, Pageable pageable);


    Page<Resource> findByCategoryEnum(CategoryEnum categoryEnum, Pageable pageable);


    @Query (value = "{'publishInfo.topicList':'?0'}")
    Page<Resource> findByTopic(String topic, Pageable pageable);

    List<Resource> findByLeaderId(String leaderId, Pageable pageable);

    List<Resource> findByFollowerIdList(String followerId, Pageable pageable);


    @Query(value = "{'id':?0}",fields = "{'name':1}")
    Resource findResourceNameById(String id);


}

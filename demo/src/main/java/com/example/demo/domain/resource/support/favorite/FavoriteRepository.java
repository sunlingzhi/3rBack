package com.example.demo.domain.resource.support.favorite;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @InterfaceName FavoriteRepository
 * @Description todo
 * @Author sun_liber
 * @Date 2019/12/10
 * @Version 1.0.0
 */
public interface FavoriteRepository extends MongoRepository<Favorite,String> {
    void deleteFavoriteByMemberNameAndResourceId(String memberName,String resourceId);
    List<Favorite> findDistinctByMemberNameAndResourceId(String memberName,String resourceID);
    List<Favorite> findDistinctByMemberName(String memberName);
}

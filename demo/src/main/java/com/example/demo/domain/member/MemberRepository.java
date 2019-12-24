package com.example.demo.domain.member;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * @InterfaceName MemberRepository
 * @Description todo
 * @Author sun_liber
 * @Date 2019/11/21
 * @Version 1.0.0
 */
public interface MemberRepository extends MongoRepository<Member, String> {
    Optional<Member> findByName(String name);
}

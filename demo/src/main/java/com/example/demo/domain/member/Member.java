package com.example.demo.domain.member;


import base.entity.BaseEntity;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @ClassName Member
 * @Description todo
 * @Author sun_liber
 * @Date 2019/11/21
 * @Version 1.0.0
 */
@Data
@Builder
@Document
public class Member extends BaseEntity {
    @Id
    private String id;
    private String name;
    private String password;
    private String email;
    private String location;
    private String website;
    private String info;
    private String avatar;
    private String groupId;
}

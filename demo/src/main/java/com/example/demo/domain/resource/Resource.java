package com.example.demo.domain.resource;

import base.entity.BaseEntity;
import com.example.demo.domain.resource.support.Activity;
import com.example.demo.domain.resource.support.Book;
import com.example.demo.domain.resource.support.CategoryEnum;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @ClassName Resource
 * @Description todo
 * @Author sun_liber
 * @Date 2019/11/27
 * @Version 1.0.0
 */
@Data
@Document
@JsonTypeInfo (use = JsonTypeInfo.Id.NAME, property = "type", visible = true, defaultImpl = Activity.class)
@JsonSubTypes ({@JsonSubTypes.Type (value = Activity.class, name = "activity")
        , @JsonSubTypes.Type (value = Book.class, name = "book")
})
public class Resource extends BaseEntity {
    private String id;
    @ApiModelProperty (value = "类型", name = "type", required = true, allowableValues = "activity or book")
    private String type;
    private String name;
    private String memberName;
    private CategoryEnum categoryEnum;
    private String content;

    private String des;
    private List<String> topicList;

    private Integer thumbsUpCount;
    private List<String> commentIdList;
    private String leaderId;
    private String leaderName;
    private List<String> followerIdList;
    private String preview;
}

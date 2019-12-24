package com.example.demo.domain.resource.vo;

import com.example.demo.domain.resource.support.CategoryEnum;
import com.example.demo.domain.resource.support.comment.Comment;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ResourceDetailVO
 * @Description todo
 * @Author sun_liber
 * @Date 2019/11/28
 * @Version 1.0.0
 */
@Data
public class ResourceDetailVO {
    private String id;
    private String type;
    private String name;
    private String memberName;
    private CategoryEnum categoryEnum;
    private String des;
    private List<String> topicList;
    @JsonFormat (pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date createTime;
    @JsonFormat (pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date updateTime;

    private Integer thumbsUpCount;
    private List<Comment> comments;
    private Boolean isFavorite;

    private Map interlinking;
}

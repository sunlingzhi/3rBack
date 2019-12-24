package com.example.demo.domain.resource.support.comment;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @ClassName Comment
 * @Description todo
 * @Author sun_liber
 * @Date 2019/11/27
 * @Version 1.0.0
 */
@Data
@Document
public class Comment {
    private String id;
    private String memberName;
    private String text;
    private Date createTime;
}

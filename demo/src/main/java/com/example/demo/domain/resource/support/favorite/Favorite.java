package com.example.demo.domain.resource.support.favorite;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @ClassName Favorite
 * @Description todo
 * @Author sun_liber
 * @Date 2019/12/10
 * @Version 1.0.0
 */
@Data
@Document
public class Favorite {
    private String id;
    private String memberName;
    private String resourceId;
    @JsonFormat (pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date createTime;
}

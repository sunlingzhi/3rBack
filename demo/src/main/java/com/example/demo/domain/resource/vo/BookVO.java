package com.example.demo.domain.resource.vo;

import cn.hutool.json.JSONObject;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName BookVO
 * @Description ass we see
 * @Author sun_liber
 * @Date 2019/12/17
 * @Version 1.0.0
 */
@Data
public class BookVO {
    private String id;
    private String name;
    private String memberName;
    private String content;
    private Date createTime;
    private Integer thumbsUpCount;
    private String leaderId;
    private String leaderName;
    private String preview;
    private Map<String,String> follower;
    private List<JSONObject> chapters;
}

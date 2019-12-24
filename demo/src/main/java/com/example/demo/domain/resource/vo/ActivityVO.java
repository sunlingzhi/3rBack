package com.example.demo.domain.resource.vo;

import com.example.demo.domain.resource.support.ActivityEnum;
import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * @ClassName ActivityVO
 * @Description leader follower 注意这里的意思是 folk or folked
 * @Author sun_liber
 * @Date 2019/12/17
 * @Version 1.0.0
 */
@Data
public class ActivityVO {
    private String pid;
    private ActivityEnum activityEnum;

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
}

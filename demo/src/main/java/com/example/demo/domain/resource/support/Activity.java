package com.example.demo.domain.resource.support;

import com.example.demo.domain.resource.Resource;
import lombok.Data;

/**
 * @ClassName Activity
 * @Description pid是对应活动的id，往往是其他平台提供的标识id，且是已经装载了内容的instance
 * @Author sun_liber
 * @Date 2019/11/27
 * @Version 1.0.0
 * @Version 1.0.0
 */
@Data
public class Activity extends Resource {
    private String pid;
    private ActivityEnum activityEnum;
}

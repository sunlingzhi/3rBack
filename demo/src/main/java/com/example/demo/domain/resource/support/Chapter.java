package com.example.demo.domain.resource.support;

import lombok.Data;

import java.util.List;

/**
 * @ClassName Chapter
 * @Description 章节名若只包含一个Activity就直接继承Activity的Name，反之需要提供一个name。
 * @Author sun_liber
 * @Date 2019/11/27
 * @Version 1.0.0
 */
@Data
public class Chapter {
    String name;
    List<String> allActivityIdList;
    ActivityCombine activityCombine;
}

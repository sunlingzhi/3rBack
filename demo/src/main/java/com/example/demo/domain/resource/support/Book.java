package com.example.demo.domain.resource.support;

import com.example.demo.domain.resource.Resource;
import lombok.Data;

import java.util.List;

/**
 * @ClassName Book
 * @Description todo
 * @Author sun_liber
 * @Date 2019/11/27
 * @Version 1.0.0
 */
@Data
public class Book extends Resource {
    private List<Chapter> chapterList;
}

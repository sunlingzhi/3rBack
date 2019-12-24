package com.example.demo.domain.resource.dto;

import com.example.demo.domain.resource.Resource;
import com.example.demo.domain.resource.support.Chapter;
import lombok.Data;

import java.util.List;

/**
 * @ClassName AddBookDTO
 * @Description todo
 * @Author sun_liber
 * @Date 2019/12/5
 * @Version 1.0.0
 */
@Data
public class AddBookDTO extends Resource {
    private List<Chapter> chapterList;
}

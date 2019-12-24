package com.example.demo.domain.resource.vo;

import com.example.demo.domain.resource.support.CategoryEnum;
import lombok.Data;

/**
 * @ClassName ResourceListVO
 * @Description todo
 * @Author sun_liber
 * @Date 2019/12/9
 * @Version 1.0.0
 */
@Data
public class ResourceListVO {
    private String id;
    private String type;
    private String name;
    private String memberName;
    private CategoryEnum categoryEnum;
}

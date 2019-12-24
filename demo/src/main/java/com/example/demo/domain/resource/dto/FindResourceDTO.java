package com.example.demo.domain.resource.dto;

import base.dto.SplitPageDTO;
import lombok.Data;

/**
 * @ClassName FindResourceDTO
 * @Description todo
 * @Author sun_liber
 * @Date 2019/12/4
 * @Version 1.0.0
 */
@Data
public class FindResourceDTO extends SplitPageDTO {
    QueryTypeEnum type;
    String value;
}

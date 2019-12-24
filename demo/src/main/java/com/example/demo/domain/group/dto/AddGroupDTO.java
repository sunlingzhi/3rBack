package com.example.demo.domain.group.dto;

import base.dto.ToDomainConverter;
import com.example.demo.domain.group.Group;
import lombok.Data;

/**
 * @ClassName AddGroupDTO
 * @Description todo
 * @Author sun_liber
 * @Date 2019/11/27
 * @Version 1.0.0
 */
@Data
public class AddGroupDTO implements ToDomainConverter<Group> {
    private String name;
    private String des;
}

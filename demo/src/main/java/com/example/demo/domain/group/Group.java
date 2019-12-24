package com.example.demo.domain.group;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @ClassName Group
 * @Description todo
 * @Author sun_liber
 * @Date 2019/11/27
 * @Version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Group {
    private String id;
    private String name;
    private String des;
}

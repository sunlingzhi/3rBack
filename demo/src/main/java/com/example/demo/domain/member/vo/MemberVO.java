package com.example.demo.domain.member.vo;

import com.example.demo.domain.group.Group;
import lombok.Data;

/**
 * @ClassName MemberVO
 * @Description todo
 * @Author sun_liber
 * @Date 2019/11/28
 * @Version 1.0.0
 */
@Data
public class MemberVO {
    private String id;
    private String name;
    private String email;
    private String location;
    private String website;
    private String info;
    private String avatar;
    private Group group;
}

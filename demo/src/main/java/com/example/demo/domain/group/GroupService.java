package com.example.demo.domain.group;

import com.example.demo.domain.group.dto.AddGroupDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName GroupServiceImpl
 * @Description todo
 * @Author sun_liber
 * @Date 2019/11/27
 * @Version 1.0.0
 */
@Service
public class GroupService {

    @Autowired
    GroupRepository groupRepository;


    public Group add(AddGroupDTO addDTO) {
        Group group = new Group();
        addDTO.convertTo(group);
        return groupRepository.insert(group);
    }


    public Group get(String id) {
        return groupRepository.findById(id).orElseGet(null);
    }

}

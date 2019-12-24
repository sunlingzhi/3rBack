package com.example.demo.domain.group;

import base.JsonResult;
import base.ResultUtils;
import com.example.demo.domain.group.dto.AddGroupDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName GroupController
 * @Description todo
 * @Author sun_liber
 * @Date 2019/11/27
 * @Version 1.0.0
 */
@RestController
@RequestMapping ("/group")
public class GroupController {

    @Autowired
    GroupService groupService;

    @RequestMapping (value = "", method = RequestMethod.POST)
    public JsonResult add(@RequestBody AddGroupDTO a) {
        return ResultUtils.success(groupService.add(a));
    }

}

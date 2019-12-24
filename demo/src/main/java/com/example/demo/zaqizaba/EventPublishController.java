package com.example.demo.zaqizaba;

import base.JsonResult;
import base.ResultUtils;
import com.example.demo.zaqizaba.event.CustomEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName EventPublishController
 * @Description todo
 * @Author sun_liber
 * @Date 2019/11/22
 * @Version 1.0.0
 */
@RestController
@RequestMapping ("event")
public class EventPublishController {

    @Autowired
    ApplicationEventPublisher publisher;

    @RequestMapping (value = "/publish", method = RequestMethod.GET)
    public JsonResult eventPublish() {
        publisher.publishEvent(new CustomEvent<>("触发了某个事件"));
        return ResultUtils.success("触发了某个事件");
    }
}

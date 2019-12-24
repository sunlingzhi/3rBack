package com.example.demo.domain.resource;

import base.JsonResult;
import base.ResultUtils;
import com.example.demo.annotation.JwtTokenParser;
import com.example.demo.domain.resource.dto.FindResourceDTO;
import com.example.demo.domain.resource.support.Activity;
import com.example.demo.domain.resource.support.Book;
import com.example.demo.domain.resource.support.comment.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName ResourceController
 * @Description todo
 * @Author sun_liber
 * @Date 2019/11/27
 * @Version 1.0.0
 */
@RestController
@RequestMapping ("/resource")
public class ResourceController {
    @Autowired
    ResourceService resourceService;

    @Value ("${web.upload-path}")
    String path;

    @RequestMapping (value = "/activity", method = RequestMethod.POST)
    JsonResult addActivity(@RequestBody Activity activity) {
        return ResultUtils.success(resourceService.add(activity));
    }

    @RequestMapping (value = "/book", method = RequestMethod.POST)
    JsonResult addBook(@RequestBody Book book) {
        return ResultUtils.success(resourceService.add(book));
    }

    @RequestMapping (value = "/preview", method = RequestMethod.POST)
    JsonResult addPreview(@RequestParam ("file") MultipartFile file) throws IOException {
        String key= UUID.randomUUID().toString();
        Files.copy(file.getInputStream(),Paths.get(path,key), StandardCopyOption.REPLACE_EXISTING);
        return ResultUtils.success(key);
    }


    @RequestMapping (value = "/{id}", method = RequestMethod.DELETE)
    JsonResult delete(@PathVariable ("id") String id) {
        resourceService.delete(id);
        return ResultUtils.success("删除成功");
    }

    @RequestMapping (value = "", method = RequestMethod.GET)
    JsonResult list(FindResourceDTO findDTO) {
        return ResultUtils.success(resourceService.list(findDTO));
    }

    @RequestMapping (value = "/activity", method = RequestMethod.GET)
    JsonResult listActivity(
            @JwtTokenParser(key="name")  String currentMemberName,
            @RequestParam("value")String value) {
        return ResultUtils.success(resourceService.listActivity(value,currentMemberName));
    }

    @RequestMapping (value = "/activity/{id}", method = RequestMethod.GET)
    JsonResult getActivity(
            @PathVariable("id") String id
            ) {
        return ResultUtils.success(resourceService.getActivity(id));
    }

    @RequestMapping (value = "/list/4EveryTopic", method = RequestMethod.POST)
    JsonResult list4EveryTopic(@RequestBody List<String> topics) {
        return ResultUtils.success(resourceService.list4EveryTopic(topics));
    }

    @RequestMapping (value = "/{id}/detail", method = RequestMethod.GET)
    public JsonResult detail(@PathVariable("id") String id,
                             @JwtTokenParser(key="name")  String currentMemberName) {
        return ResultUtils.success(resourceService.getDetail(id,currentMemberName));
    }

    @RequestMapping (value = "/{id}/activity", method = RequestMethod.GET)
    public JsonResult interactionActivity(@PathVariable("id") String id) {
        return ResultUtils.success(resourceService.getInteractionActivity(id));
    }

    @RequestMapping (value = "/{id}/book", method = RequestMethod.GET)
    public JsonResult interactionBook(@PathVariable("id") String id) {
        return ResultUtils.success(resourceService.getInteractionBook(id));
    }


    @RequestMapping (value = "/getBlocks", method = RequestMethod.POST)
    public JsonResult getActivityBlock(@RequestBody List<String> allActivityIdList) {
        return ResultUtils.success(resourceService.linkActivityInBook(allActivityIdList));
    }






    @RequestMapping (value = "/{id}/thumbsUp", method = RequestMethod.PUT)
    JsonResult thumbsUp(@PathVariable ("id") String id) {
        return ResultUtils.success(resourceService.thumbsUp(id));
    }

    @RequestMapping (value = "/{id}/favorite/{operation}", method = RequestMethod.PUT)
    JsonResult favoriteChange(@PathVariable ("id") String id,
                        @PathVariable("operation") Boolean operation,
                        @JwtTokenParser(key="name")  String memberName) {
        return ResultUtils.success(resourceService.favorite(id,memberName,operation));
    }


    @RequestMapping (value = "/{id}/comment", method = RequestMethod.PUT)
    JsonResult comment(@PathVariable ("id") String id,  @RequestBody Comment comment) {
        return ResultUtils.success(resourceService.comment(id, comment));
    }






}

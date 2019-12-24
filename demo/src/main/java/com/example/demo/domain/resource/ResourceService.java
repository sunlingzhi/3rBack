package com.example.demo.domain.resource;

import base.CopyUtils;
import base.exception.MyException;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.demo.domain.resource.dto.FindResourceDTO;
import com.example.demo.domain.resource.dto.QueryTypeEnum;
import com.example.demo.domain.resource.support.*;
import com.example.demo.domain.resource.support.comment.Comment;
import com.example.demo.domain.resource.support.comment.CommentRepository;
import com.example.demo.domain.resource.support.favorite.Favorite;
import com.example.demo.domain.resource.support.favorite.FavoriteRepository;
import com.example.demo.domain.resource.vo.ActivityVO;
import com.example.demo.domain.resource.vo.BookVO;
import com.example.demo.domain.resource.vo.ResourceDetailVO;
import com.example.demo.domain.resource.vo.ResourceListVO;
import com.example.demo.domain.rpc.MCClient;
import com.google.common.collect.Lists;
import domain.modelinstance.ModelInstance;
import domain.modelitem.ModelItem;
import domain.modelitem.support.IOFlagEnum;
import edu.njnu.opengms.common.utils.JavaBeanCollectionUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName ResourceServiceImpl
 * @Description todo
 * @Author sun_liber
 * @Date 2019/11/28
 * @Version 1.0.0
 */
@Service
public class ResourceService {
    @Autowired
    ResourceRepository resourceRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    FavoriteRepository favoriteRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    MCClient mcClient;


    public Resource add(Activity activity) {
        activity.setCreateTime(new Date());
        activity.setUpdateTime(new Date());
        String leaderId = activity.getLeaderId();
        Activity save = resourceRepository.save(activity);
        //修改leader
        addToLeader(save.getId(), leaderId);
        return save;
    }

    public Resource add(Book book) {
        book.setCreateTime(new Date());
        book.setUpdateTime(new Date());
        String leaderId = book.getLeaderId();
        Book save = resourceRepository.save(book);
        //修改leader
        addToLeader(save.getId(), leaderId);
        return save;
    }

    private void addToLeader(String followerId, String leaderId) {
        if (leaderId != null) {
            Resource leader = resourceRepository.findById(leaderId).orElseThrow(MyException::noObject);
            List<String> followerIdList = leader.getFollowerIdList();
            if (followerIdList == null) {
                followerIdList = new ArrayList<>();
            }
            followerIdList.add(followerId);
            leader.setFollowerIdList(followerIdList);
            resourceRepository.save(leader);
        }
    }



    public void delete(String id) {
        resourceRepository.deleteById(id);
    }

    public ResourceDetailVO getDetail(String id, String currentMemberName) {
        currentMemberName = (currentMemberName == null ? "undefined" : currentMemberName);
        Resource resource = resourceRepository.findById(id).orElse(null);
        ResourceDetailVO vo = new ResourceDetailVO();
        CopyUtils.copyProperties(resource, vo);


        List<Favorite> distinctByMemberNameAndResourceId = favoriteRepository.findDistinctByMemberNameAndResourceId(currentMemberName, id);
        if (distinctByMemberNameAndResourceId.size() == 0) {
            vo.setIsFavorite(false);
        } else {
            vo.setIsFavorite(true);
        }

        List<String> commentIdList = resource.getCommentIdList();
        if (commentIdList != null) {
            vo.setComments(Lists.newArrayList(commentRepository.findAllById(commentIdList)));
        }

        Map<String, Map> interlinking = new HashMap<>();

        Page<Resource> createTime = resourceRepository.findAll(
                PageRequest.of(0, 4, Sort.Direction.DESC, "createTime"));


        Map<String, String> stringStringMap = JavaBeanCollectionUtils.convertToMap(createTime.getContent(), Resource::getId, Resource::getName);
        interlinking.put("new", stringStringMap);


        //related资源
        //可参考，按照优先级，最终找到4个
        //1.Derive Finish
        //2.Based  Finish
        //3.Book including
        //4.Same Topic or Category TODO

        PageRequest page = PageRequest.of(0, 4, Sort.Direction.DESC, "createTime");
        List<Resource> byLeaderId = resourceRepository.findByLeaderId(id, page);

        if (byLeaderId.size() < 4) {
            page = PageRequest.of(0, 4 - byLeaderId.size(), Sort.Direction.DESC, "createTime");
        }
        List<Resource> byFollowerIdList = resourceRepository.findByFollowerIdList(id, page);
        byLeaderId.addAll(byFollowerIdList);


        if ((byLeaderId.size() + byFollowerIdList.size()) < 4) {
            page = PageRequest.of(0, 4 - byLeaderId.size() - byFollowerIdList.size(), Sort.Direction.DESC, "createTime");
        }
        Query query = new Query(Criteria.where("chapterList").elemMatch(Criteria.where("activityList").elemMatch(Criteria.where("_id").is(new ObjectId(id)))));
        List<Resource> includeInBook = mongoTemplate.find(query.with(page), Resource.class);
        byLeaderId.addAll(includeInBook);


        stringStringMap = JavaBeanCollectionUtils.convertToMap(byLeaderId, Resource::getId, Resource::getName);
        interlinking.put("related", stringStringMap);

        //可以被上游Topic和下游Topic给取代 TODO
        //但是目前的Topic只有一层的设计，因此未采纳
        //而采取同一个作者的resource
        //注意这里如果改为Topic之后，前台注意跳转到对应的Topic页面，而不是具体的detail TODO
        Page<Resource> byMemberNameAndPublishInfoExists = resourceRepository.findByMemberName(
                resource.getMemberName(),
                PageRequest.of(0, 4, Sort.Direction.DESC, "thumbsUpCount"));
        stringStringMap = JavaBeanCollectionUtils.convertToMap(byMemberNameAndPublishInfoExists.getContent(), Resource::getId, Resource::getName);
        interlinking.put("discover", stringStringMap);

        vo.setInterlinking(interlinking);
        return vo;
    }

    public ActivityVO getInteractionActivity(String id) {
        Activity activity = (Activity) resourceRepository.findById(id).orElse(null);
        ActivityVO vo = new ActivityVO();
        CopyUtils.copyProperties(activity, vo);
        vo.setFollower(getFollower(activity.getFollowerIdList()));
        return vo;
    }

    private Map<String, String> getFollower(List<String> followerIdList) {
        if (followerIdList == null || followerIdList.size() == 0) {
            return null;
        }
        Map<String, String> follower = new HashMap<>();
        followerIdList.stream().forEach((followerId) -> {
            String name = resourceRepository.findResourceNameById(followerId).getName();
            follower.put(followerId, name);
        });
        return follower;
    }

    public BookVO getInteractionBook(String id) {
        Book book = (Book) resourceRepository.findById(id).orElse(null);
        BookVO vo = new BookVO();
        CopyUtils.copyProperties(book, vo);
        vo.setFollower(getFollower(book.getFollowerIdList()));

        List<JSONObject> chapters = new ArrayList<>();
        for (Chapter chapter : book.getChapterList()) {
            JSONObject obj = new JSONObject();
            obj.put("name", chapter.getName());

            //TODO
            //目前是全部返回可以考虑逐步加载
            JSONArray array = new JSONArray();
            chapter.getAllActivityIdList().stream().forEach(activityId -> {
                array.add(getInteractionActivity(activityId));
            });
            obj.put("allActivity", array);

            //TODO
            //combine
            obj.put("activityCombine", chapter.getActivityCombine());
            chapters.add(obj);
        }
        vo.setChapters(chapters);
        return vo;
    }

    public Page<ResourceListVO> list(FindResourceDTO findDTO) {
        String value = findDTO.getValue();
        Pageable pageable = findDTO.getPageable();
        QueryTypeEnum type = findDTO.getType() != null ? findDTO.getType() : QueryTypeEnum.DEFAULT;
        Page<Resource> resourcePage;

        switch (type) {
            case NAME: {
                resourcePage = resourceRepository.findByNameContains(value,  pageable);
                break;
            }
            case MEMBER: {
                resourcePage = resourceRepository.findByMemberName(value, pageable);
                break;
            }
            case CATEGORY: {
                resourcePage = resourceRepository.findByCategoryEnum(CategoryEnum.valueOf(value), pageable);
                break;
            }
            case TOPIC: {
                //有Topic意味着必然是Publish
                resourcePage = resourceRepository.findByTopic(value, pageable);
                break;
            }
            default: {
                resourcePage = resourceRepository.findAll( pageable);
                break;
            }
        }

        return resourcePage.map(resource -> {
            ResourceListVO vo = new ResourceListVO();
            CopyUtils.copyProperties(resource, vo);
            return vo;
        });
    }


    public Resource get(String id){
        return resourceRepository.findById(id).orElse(null);
    }

    public HashMap<String, List<ResourceDetailVO>> list4EveryTopic(List<String> topics) {
        Map<String, List<ResourceDetailVO>> map = new HashMap<>();
        FindResourceDTO findResourceDTO = new FindResourceDTO();
        findResourceDTO.setPageSize(4);
        for (String topic : topics) {
            List<ResourceDetailVO> collect = resourceRepository.findByTopic(topic, findResourceDTO.getPageable()).get().map(resource -> {
                ResourceDetailVO vo = new ResourceDetailVO();
                CopyUtils.copyProperties(resource, vo);
                return vo;
            }).collect(Collectors.toList());
            map.put(topic, collect);
        }
        return (HashMap<String, List<ResourceDetailVO>>) map;
    }

    public Integer thumbsUp(String id) {
        Resource resource = resourceRepository.findById(id).orElseThrow(MyException::noObject);
        Integer thumbsUpCount = resource.getThumbsUpCount();

        if (thumbsUpCount == null) {
            thumbsUpCount = 0;
        }

        resource.setThumbsUpCount(++thumbsUpCount);
        resourceRepository.save(resource);
        return thumbsUpCount;
    }


    public Comment comment(String id, Comment comment) {
        comment.setCreateTime(new Date());
        Comment save = commentRepository.save(comment);
        Resource resource = resourceRepository.findById(id).orElseThrow(MyException::noObject);

        List<String> commentIdList = resource.getCommentIdList();
        if (commentIdList == null) {
            commentIdList = new ArrayList<>();
        }
        commentIdList.add(save.getId());

        resource.setCommentIdList(commentIdList);
        resourceRepository.save(resource);

        return comment;

    }

    public Favorite favorite(String id, String memberName, Boolean operation) {
        memberName = (memberName == null ? "undefined" : memberName);
        if (operation) {
            Favorite favorite = new Favorite();
            favorite.setResourceId(id);
            favorite.setMemberName(memberName);
            favorite.setCreateTime(new Date());
            return favoriteRepository.save(favorite);
        } else {
            favoriteRepository.deleteFavoriteByMemberNameAndResourceId(memberName, id);
            return null;
        }

    }

    public JSONArray linkActivityInBook(List<String> activityIdList) {
        ArrayList<Resource> resources = Lists.newArrayList(resourceRepository.findAllById(activityIdList));
        JSONArray jsonArray = new JSONArray();
        resources.stream().forEach(resource->{
            Activity activity = (Activity) resource;
            String pid = activity.getPid();
            ActivityEnum activityEnum = activity.getActivityEnum();
            JSONObject jsonObject = new JSONObject();
            ModelInstance modelInstance = JSONUtil.toBean(JSONUtil.toJsonStr(mcClient.getModelInstance(pid).getData()), ModelInstance.class);
            ModelItem modelItem = modelInstance.getModelItem();
            jsonObject.put("name", modelItem.getName());
            jsonObject.put("activityEnum", activityEnum);
            JSONArray fields = new JSONArray();
            modelItem.getStateList().stream().forEach(state -> {
                state.getEventList().stream().forEach(event -> {
                    JSONObject field = new JSONObject();
                    String name = event.getName();
                    field.put("name", name);
                    if (event.getIoFlagEnum().equals(IOFlagEnum.INPUT)) {
                        if (event.getDataTemplate().getType().equals("file")) {
                            field.put("attr", "input");
                        } else {
                            field.put("attr", "property");
                            field.put("value", event.getDataTemplate().getValue());
                        }
                    } else {
                        field.put("attr", "output");
                    }
                    fields.add(field);
                });
            });
            jsonObject.put("fields", fields);
            jsonArray.add(jsonObject);
        });
        return jsonArray;
    }

    public Resource saveLinkActivity(String id,String chapterName,ActivityCombine activityCombine) {
        Book book =(Book) resourceRepository.findById(id).orElseThrow(MyException::noObject);
        List<Chapter> chapterList = book.getChapterList();
        for (int i = 0; i < chapterList.size(); i++) {
            Chapter chapter = chapterList.get(i);
            if(chapterName.equals(chapter.getName())){
                chapter.setActivityCombine(activityCombine);
                chapterList.set(i,chapter);
                break;
            }
        }
        book.setChapterList(chapterList);
        return resourceRepository.save(book);
    }

    public Map<String,String> listActivity(String value,String memberName) {
        List<ResourceListVO> distinctByNameContainsAndMemberName = resourceRepository.findDistinctByNameContainsAndMemberNameAndType(value, memberName,"activity");
        Map<String, String> stringStringMap = JavaBeanCollectionUtils.convertToMap(distinctByNameContainsAndMemberName, ResourceListVO::getId, ResourceListVO::getName);
        return stringStringMap;
    }

    public Resource getActivity(String id) {
        return  resourceRepository.findById(id).orElse(null);
    }
}

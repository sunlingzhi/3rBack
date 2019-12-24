package com.example.demo.domain.member;


import base.CopyUtils;
import base.enums.ResultEnum;
import base.exception.MyException;
import com.example.demo.domain.group.GroupService;
import com.example.demo.domain.member.vo.MemberVO;
import com.example.demo.domain.resource.Resource;
import com.example.demo.domain.resource.ResourceService;
import com.example.demo.domain.resource.support.favorite.Favorite;
import com.example.demo.domain.resource.support.favorite.FavoriteRepository;
import com.example.demo.domain.resource.vo.ResourceListVO;
import edu.njnu.opengms.common.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName UserService
 * @Description todo
 * @Author sun_liber
 * @Date 2019/11/21
 * @Version 1.0.0
 */
@Service
public class MemberService {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    GroupService groupService;

    @Autowired
    FavoriteRepository favoriteRepository;

    @Autowired
    ResourceService resourceService;


    public Member register(String name,String password,String email) {
        if(memberRepository.findByName(name).isPresent()){
            throw new MyException(ResultEnum.EXIST_OBJECT);
        }
        Member member=Member.builder().name(name).password(password).email(email).build();
        member.beforeInsert();
        return memberRepository.insert(member);
    }

    public String login(String name,String password) {
        Member memberFromDB = memberRepository.findByName(name).orElseThrow(MyException::noObject);
        if(memberFromDB.getPassword().equals(password)){
            String jwtToken = JwtUtils.generateToken(memberFromDB.getId(), name, password);
            return "Bearer" + " " + jwtToken;
        }else{
            throw new MyException(ResultEnum.USER_PASSWORD_NOT_MATCH);
        }
    }

    public MemberVO get(String id){
        MemberVO vo=new MemberVO();
        memberRepository.findById(id).ifPresent(member -> {
            CopyUtils.copyProperties(member,vo);
            String groupId = member.getGroupId();
            vo.setGroup(groupService.get(groupId));
        });
        return vo;
    }


    public Member update(String id, Member member) {
        Member memberInDB = memberRepository.findById(id).orElseThrow(MyException::noObject);
        CopyUtils.copyProperties(member,memberInDB);
        memberInDB.setUpdateTime(new Date());
        return memberRepository.save(memberInDB);
    }


    public Member getByName(String s) {
        return memberRepository.findByName(s).orElse(null);
    }

    public MemberVO getVOByName(String s) {
        MemberVO vo=new MemberVO();
        memberRepository.findByName(s).ifPresent(member -> {
            CopyUtils.copyProperties(member,vo);
            String groupId = member.getGroupId();
            vo.setGroup(groupService.get(groupId));
        });
        return vo;
    }

    public List<ResourceListVO> getFavoriteResource(String name) {
        List<ResourceListVO> resourceListVOList=new ArrayList<>();
        List<Favorite> favorites = favoriteRepository.findDistinctByMemberName(name);
        return favorites.stream().map(favorite -> {
            Resource resource = resourceService.get(favorite.getResourceId());
            ResourceListVO vo = new ResourceListVO();
            CopyUtils.copyProperties(resource, vo);
            return vo;
        }).collect(Collectors.toList());
    }
}

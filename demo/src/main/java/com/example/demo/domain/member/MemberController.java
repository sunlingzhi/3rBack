package com.example.demo.domain.member;

import base.JsonResult;
import base.ResultUtils;
import com.example.demo.annotation.JwtTokenParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName UserController
 * @Description todo
 * @Author sun_liber
 * @Date 2019/11/21
 * @Version 1.0.0
 */
@RestController
@RequestMapping (value = "/member")
public class MemberController {

    @Autowired
    MemberService memberService;

    @RequestMapping (value = "/register", method = RequestMethod.POST)
    public JsonResult doRegister(@RequestParam ("name") String name,
                                 @RequestParam ("password") String password,
                                 @RequestParam ("email") String email
    ) {

        return ResultUtils.success(memberService.register(name, password, email));
    }

    @RequestMapping (value = "/login", method = RequestMethod.POST)
    public JsonResult doLogin(@RequestParam ("name") String name,
                              @RequestParam ("password") String password) {
        return ResultUtils.success(memberService.login(name, password));
    }

    @RequestMapping (value = "/{id}", method = RequestMethod.GET)
    public JsonResult get(@PathVariable ("id") String id) {
        return ResultUtils.success(memberService.get(id));
    }

    @RequestMapping (value = "/getByName/{name}", method = RequestMethod.GET)
    public JsonResult getByName(@PathVariable ("name") String name) {
        return ResultUtils.success(memberService.getVOByName(name));
    }

    @RequestMapping (value = "/{id}", method = RequestMethod.PUT)
    public JsonResult update(@PathVariable ("id") String id, @RequestBody Member member) {
        return ResultUtils.success(memberService.update(id, member));
    }

    @RequestMapping (value = "/favorite", method = RequestMethod.GET)
    JsonResult getFavoriteResource(@JwtTokenParser (key="name")  String currentMemberName) {
        return ResultUtils.success(memberService.getFavoriteResource(currentMemberName));
    }
}

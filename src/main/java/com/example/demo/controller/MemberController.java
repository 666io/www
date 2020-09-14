package com.example.demo.controller;

import com.example.demo.entity.Member;
import com.example.demo.service.MemberService;
import com.example.demo.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("/list")
    public List<Member> queryList() {
        return memberService.queryList();
    }

    @RequestMapping("/test")
    public Object test() {
        String redisKey = "crm.cache";
        Object flag = redisUtil.get(redisKey);
        System.out.println(flag);
        if (flag == null) {
            String message = "This is a message for Login info.";
            redisUtil.set(redisKey, message);
            return "The redis key has no content.New message is:"+message;
        }
        return flag;
    }

}

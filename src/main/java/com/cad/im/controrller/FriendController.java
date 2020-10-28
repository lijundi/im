package com.cad.im.controrller;

import com.alibaba.fastjson.JSONObject;
import com.cad.im.entity.mysql.User;
import com.cad.im.service.FriendService;
import com.cad.im.util.Result;
import com.cad.im.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Name: com.cad.im.controrller.FriendController
 * @Date: 2020/10/25
 * @Auther: weiwending
 * @Description: 好友页面
 */

@RestController
@RequestMapping("/friend")
public class FriendController {
    @Autowired
    FriendService friendService;

    //获取好友列表
    @GetMapping("/list")
    public Result getFriends(String identity){
        try{
           List<User> friends =  friendService.getFriends(identity);
           List res = new ArrayList();
           for (User friend : friends){
               Map map = new HashMap();
               map.put("userId", friend.getUserId());
               map.put("nickName", friend.getNickName());
               map.put("avatarUrl", friend.getAvatarUrl());
               res.add(map);
           }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("data", res);
            return Result.success(jsonObject);
        } catch (Exception ex){
            return Result.failure(ResultCode.FAILURE, ex.toString());
        }
    }
    //获取好友信息
    @GetMapping("/info")
    public Result getUserInfo(String friendId){
        try{
            User friend = friendService.getUserInfo(friendId);
            Map map = new HashMap();
            map.put("userId", friend.getUserId());
            map.put("nickName", friend.getNickName());
            map.put("avatarUrl", friend.getAvatarUrl());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("data", map);
            return Result.success(jsonObject);
        } catch (Exception ex){
            return Result.failure(ResultCode.FAILURE, ex.toString());
        }
    }
}

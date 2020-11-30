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

    //搜索获取好友与非好友列表
    @GetMapping("/find")
    public Result findFriends(String userId, String friendName) {
        try {
            return friendService.tempfindFriends(userId, friendName);
        } catch (Exception ex) {
            return Result.failure(ResultCode.FAILURE, ex.toString());
        }
    }

    //获取好友列表
    @GetMapping("/list")
    public Result getFriend(String userId) {
        return friendService.getFriends(userId);
    }

    //获取好友信息
    @GetMapping("/info")
    public Result getUserInfo(String friendId) {
        try {
            User friend = friendService.getUserInfo(friendId);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userId", friend.getUserId());
            jsonObject.put("nickName", friend.getNickName());
            jsonObject.put("avatarUrl", friend.getAvatarUrl());
            return Result.success(jsonObject);
        } catch (Exception ex) {
            return Result.failure(ResultCode.FAILURE, ex.toString());
        }
    }

    //删除好友
    @PostMapping("/del")
    public Result delFriend(String userId, String friendId) {
        return friendService.delFriend(userId, friendId);
    }

    //申请好友
    @PostMapping("/apply")
    public Result applyFriend(String userId, String friendId, String userName, String friendName) {
        return friendService.applyFriend(userId, friendId, userName, friendName);
    }

    //同意好友
    @PostMapping("/agree")
    public Result agreeFriend(String userId, String friendId) {
        return friendService.agreeFriend(userId, friendId);
    }

    //未同意好友列表
    @GetMapping("/nonfriends")
    public Result nonFriends(String userId) {
        return friendService.nonFriends(userId);
    }

}

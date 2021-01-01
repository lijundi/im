package com.cad.im.controrller;

import com.alibaba.fastjson.JSONObject;
import com.cad.im.entity.mysql.User;
import com.cad.im.entity.websocket.WsChatMessage;
import com.cad.im.service.ChatService;
import com.cad.im.service.FriendService;
import com.cad.im.service.SessionHandler;
import com.cad.im.util.Result;
import com.cad.im.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    @Autowired
    ChatService chatService;
    @Autowired
    SessionHandler sessionHandler;

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
            jsonObject.put("identity", friend.getIdentity());
            return Result.success(jsonObject);
        } catch (Exception ex) {
            return Result.failure(ResultCode.FAILURE, ex.toString());
        }
    }

    //删除好友
    @GetMapping("/del")
    public Result delFriend(String userId, String friendId) {
        return friendService.delFriend(userId, friendId);
    }

    //申请好友
    @GetMapping("/apply")
    public Result applyFriend(String userId, String friendId, String userName, String friendName) {
        try {
            if(friendService.isFriend(userId, friendId)){
                friendService.storeApplyFriend(userId, friendId, userName, friendName);
                String text = userName + "的好友申请";
                WsChatMessage wsChatMsg = new WsChatMessage("2",
                        friendId, "friend", text, new Date());
                chatService.storeChatMessage(wsChatMsg);
                if (sessionHandler.isOnline(friendId)) {
                    chatService.forwardMessage(wsChatMsg);
                }
                return Result.success();
            } else {
                return Result.success("好友申请已经提交");
            }
        } catch (Exception ex) {
            return Result.failure(ResultCode.FAILURE, ex.toString());
        }
    }

    //同意好友
    @GetMapping("/agree")
    public Result agreeFriend(String userId, String friendId) {
        return friendService.agreeFriend(userId, friendId);
    }

    //未同意好友列表
    @GetMapping("/nonfriends")
    public Result nonFriends(String userId) {
        return friendService.nonFriends(userId);
    }

}

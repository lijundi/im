package com.cad.im.service;

import com.alibaba.fastjson.JSONObject;
import com.cad.im.entity.mysql.Friend;
import com.cad.im.entity.mysql.User;
import com.cad.im.entity.mysql.UserRelation;
import com.cad.im.repository.UserRelationRepository;
import com.cad.im.repository.UserRepository;
import com.cad.im.util.Result;
import com.cad.im.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.util.*;

/**
 * @Name: com.cad.im.service.FriendService
 * @Date: 2020/10/25
 * @Auther: weiwending
 * @Description: 好友Service
 */

@Service
public class FriendService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserRelationRepository userRelationRepository;

    public Result findFriends(String userId, String friendName) {
        List<User> users = userRepository.findAllByUserIdAndNickName(userId, friendName);
        List<UserRelation> friends = userRelationRepository.findByUserAndFriend(userId, friendName);
        List<JSONObject> retnonfriends = new ArrayList<>();
        List<JSONObject> retfriends = new ArrayList<>();
        Map map = new HashMap();
        users.remove(0);
        for (User user : users) {
            Friend friend = new Friend(userId, user.getUserId(), user.getNickName(), user.getAvatarUrl(), false);
            map.put(user.getUserId(), friend);
        }
        for (UserRelation userRelation : friends) {
            Friend friend = (Friend) map.get(userRelation.getFriendId());
            friend.setStatus(true);
            map.put(userRelation.getFriendId(), friend);
        }
        Iterator<Map.Entry<String, Friend>> iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, Friend> entry = iter.next();
            Friend friend = (Friend) entry.getValue();
            if (friend.getStatus()) {
                JSONObject friendMap = new JSONObject();
                friendMap.put("userId", friend.getFriendId());
                friendMap.put("nickName", friend.getNickName());
                friendMap.put("avatarUrl", friend.getAvatarUrl());
                retfriends.add(friendMap);
            } else {
                JSONObject nonfriendMap = new JSONObject();
                nonfriendMap.put("userId", friend.getFriendId());
                nonfriendMap.put("nickName", friend.getNickName());
                nonfriendMap.put("avatarUrl", friend.getAvatarUrl());
                retnonfriends.add(nonfriendMap);
            }
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("non-friends", retnonfriends);
        jsonObject.put("friends", retfriends);
        return Result.success(jsonObject);
    }

    //临时模糊查询好友,后期需要删除
    public Result tempfindFriends(String userId, String friendName) {
        List<User> users = userRepository.findAll();
        List<UserRelation> friends = userRelationRepository.findByUserIdAndStatus(userId, true);
        List<User> tempusers = new ArrayList<>();
        List<UserRelation> tempfriends = new ArrayList<>();
        List retnonfriends = new ArrayList<>();
        List retfriends = new ArrayList<>();
        Map map = new HashMap();
        users.remove(0);
        for (User user : users) {
            if (user.getNickName().contains(friendName)) {
                tempusers.add(user);
            }
        }
        for (User user : tempusers) {
            if (!userId.equals(user.getUserId())) {
                Friend friend = new Friend(userId, user.getUserId(), user.getNickName(), user.getAvatarUrl(), false);
                map.put(user.getUserId(), friend);
            }
        }
        for (UserRelation userRelation : friends) {
            if (userRelation.getFriendName().contains(friendName)) {
                tempfriends.add(userRelation);
            }
        }
        for (UserRelation userRelation : tempfriends) {
            Friend friend = (Friend) map.get(userRelation.getFriendId());
            friend.setStatus(true);
            map.put(userRelation.getFriendId(), friend);
        }
        Iterator<Map.Entry<String, Friend>> iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, Friend> entry = iter.next();
            Friend friend = entry.getValue();
            if (friend.getStatus()) {
                JSONObject friendMap = new JSONObject();
                friendMap.put("userId", friend.getFriendId());
                friendMap.put("nickName", friend.getNickName());
                friendMap.put("avatarUrl", friend.getAvatarUrl());
                retfriends.add(friendMap);
            } else {
                JSONObject nonfriendMap = new JSONObject();
                nonfriendMap.put("userId", friend.getFriendId());
                nonfriendMap.put("nickName", friend.getNickName());
                nonfriendMap.put("avatarUrl", friend.getAvatarUrl());
                retnonfriends.add(nonfriendMap);
            }
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("non-friends", retnonfriends);
        jsonObject.put("friends", retfriends);
        return Result.success(jsonObject);
    }

    public Result getFriends(String userId) {
        try {
            List<User> users = userRepository.findAll();
            List<UserRelation> friends = userRelationRepository.findByUserIdAndStatus(userId, true);
            List retfriends = new ArrayList<>();
            users.remove(0);
            Map map = new HashMap();
            for (User user : users) {
                Friend friend = new Friend(userId, user.getUserId(), user.getNickName(), user.getAvatarUrl(), false);
                map.put(user.getUserId(), friend);
            }
            for (UserRelation userRelation : friends) {
                Friend friend = (Friend) map.get(userRelation.getFriendId());
                friend.setStatus(true);
                map.put(userRelation.getFriendId(), friend);
            }
            Iterator<Map.Entry<String, Friend>> iter = map.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry<String, Friend> entry = iter.next();
                Friend friend = (Friend) entry.getValue();
                if (friend.getStatus()) {
                    Map friendMap = new HashMap();
                    friendMap.put("userId", friend.getFriendId());
                    friendMap.put("nickName", friend.getNickName());
                    friendMap.put("avatarUrl", friend.getAvatarUrl());
                    retfriends.add(friendMap);
                }
            }
            return Result.success(retfriends);
        } catch (Exception ex) {
            return Result.failure(ResultCode.FAILURE, ex.toString());
        }
    }

    public User getUserInfo(String userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public Result delFriend(String userId, String friendId) {
        try {
            userRelationRepository.delByUserAndFriend(userId, friendId);
            userRelationRepository.delByUserAndFriend(friendId, userId);
        } catch (Exception ex) {
            return Result.failure(ResultCode.FAILURE, ex.toString());
        }
        return Result.success();
    }

    public Result applyFriend(String userId, String friendId, String userName, String friendName) {
        try {
            if (userRelationRepository.findByUserIdAndFriendId(userId, friendId).isEmpty()) {
                UserRelation userRelation = new UserRelation(userId, friendId, friendName, false);
                UserRelation friendRelation = new UserRelation(friendId, userId, userName, false);
                userRelationRepository.save(userRelation);
                userRelationRepository.save(friendRelation);
            } else {
                return Result.success("好友申请已经提交");
            }
        } catch (Exception ex) {
            return Result.failure(ResultCode.FAILURE, ex.toString());
        }
        return Result.success();
    }

    public Result agreeFriend(String userId, String friendId) {
        try {
            userRelationRepository.updateRelation(userId, friendId);
            userRelationRepository.updateRelation(friendId, userId);
        } catch (Exception ex) {
            return Result.failure(ResultCode.FAILURE, ex.toString());
        }
        return Result.success();
    }

    public Result nonFriends(String userId) {
        try {
            return Result.success(userRelationRepository.findByUserIdAndStatus(userId, false));
        } catch (Exception ex) {
            return Result.failure(ResultCode.FAILURE, ex.toString());
        }
    }
}

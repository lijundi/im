package com.cad.im.entity.http;

import lombok.Data;

/**
 * @Name: com.cad.im.entity.mysql.Friend
 * @Date: 2020/11/30
 * @Auther: weiwending
 * @Description: 记录所有好友与非好友
 */

@Data
public class FriendInfo {
    private String userId;
    private String friendId;
    private String nickName;
    private String avatarUrl;
    private String identity;
    private Boolean status;

    public FriendInfo() {
    }

    public FriendInfo(String userId, String friendId, String nickName, String avatarUrl, String identity, Boolean status) {
        this.userId = userId;
        this.friendId = friendId;
        this.nickName = nickName;
        this.avatarUrl = avatarUrl;
        this.identity = identity;
        this.status = status;
    }
}

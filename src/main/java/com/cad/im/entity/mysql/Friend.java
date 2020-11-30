package com.cad.im.entity.mysql;

import lombok.Data;

import java.util.Base64;

/**
 * @Name: com.cad.im.entity.mysql.Friend
 * @Date: 2020/11/30
 * @Auther: weiwending
 * @Description: 记录所有好友与非好友
 */

@Data
public class Friend {
    private String userId;
    private String friendId;
    private String nickName;
    private String avatarUrl;
    private Boolean status;

    public Friend() {
    }

    public Friend(String userId, String friendId, String nickName, String avatarUrl, Boolean status) {
        this.userId = userId;
        this.friendId = friendId;
        this.nickName = nickName;
        this.avatarUrl = avatarUrl;
        this.status = status;
    }
}

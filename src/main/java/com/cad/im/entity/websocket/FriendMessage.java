package com.cad.im.entity.websocket;

import lombok.Data;

/**
 * @Name: com.cad.im.entity.websocket.FriendMessage
 * @Date: 2020/11/29
 * @Auther: weiwending
 * @Description: 好友信息
 */

@Data
public class FriendMessage {
    private String userId;
    private String friendId;
    private String userName;
    private String friendName;
}

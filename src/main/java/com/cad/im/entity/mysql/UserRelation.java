package com.cad.im.entity.mysql;

import lombok.Data;

import javax.persistence.*;
import java.util.Base64;


@Data
@Entity
public class UserRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userRelation_id; // 自增主键
    private String userId;
    private String friendId;
    private String friendName;
    private Boolean status; // true--成为好友，false--好友请求
    private String symbol;

    public UserRelation() {

    }

    public UserRelation(String userId, String friendId, String friendName, Boolean status) {
        this.userId = userId;
        this.friendId = friendId;
        this.friendName = new String(Base64.getEncoder().encode(friendName.getBytes()));
        this.status = status;
    }

    public UserRelation(String userId, String friendId, String friendName, Boolean status, String symbol) {
        this.userId = userId;
        this.friendId = friendId;
        this.friendName = new String(Base64.getEncoder().encode(friendName.getBytes()));
        this.status = status;
        this.symbol = symbol;
    }

    public String getFriendName() {
        return new String(Base64.getDecoder().decode(this.friendName.getBytes()));
    }
}

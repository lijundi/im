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

    public UserRelation(){

    }

    public UserRelation(String userId, String friendId, String friendName, Boolean status) {
        this.userId = userId;
        this.friendId = friendId;
        this.friendName = friendName;
        this.status = status;
    }
    public String getFriendName(){
        return new String(Base64.getDecoder().decode(this.friendName.getBytes()));
    }

}

package com.cad.im.entity.mysql;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Data
@Entity
public class UserRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userRelation_id; // 自增主键
    private Integer user1Id;
    private Integer user2Id;
    private Boolean Status; // true--成为好友，false--好友请求
}

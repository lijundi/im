package com.cad.im.entity.mysql;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author lijundi
 * @date 2020/1/8 12:18
 */
@Data
@Entity
public class UserRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userRelation_id; // 自增主键
    private Integer user1_id;
    private Integer user2_id;
    private Boolean Status; // true--成为好友，false--好友请求
}

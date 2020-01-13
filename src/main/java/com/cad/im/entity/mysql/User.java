package com.cad.im.entity.mysql;

import lombok.Data;

import javax.persistence.*;

/**
 * @author lijundi
 * @date 2020/1/8 12:17
 */
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id; // 自增主键
    private String nick_name; // 用户昵称
    private String avatar_url; // 头像
    private String gender; // 性别--'男','女'
    // 微信登录
    private String open_id;
    private String session;
}

package com.cad.im.entity.mysql;

import com.cad.im.entity.profile.BasicInformation;
import com.cad.im.entity.profile.ChiefComplaint;
import lombok.Data;

import javax.persistence.*;


@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId; // 自增主键
    private String nickName; // 用户昵称
    private String avatarUrl; // 头像
    private String identity; // 身份--'patient','doctor'
    // 微信登录
    private String openId;
    private String session;

    public User(){}

    public User(String nickName, String avatarUrl){
        this.nickName = nickName;
        this.avatarUrl = avatarUrl;
    }
//    @OneToOne(targetEntity = BasicInformation.class,
//            cascade = CascadeType.ALL,
//            mappedBy = "user")
//    private BasicInformation basicInformation;
//
//    @OneToOne(targetEntity = ChiefComplaint.class,
//            cascade = CascadeType.ALL,
//            mappedBy = "user")
//    private ChiefComplaint chiefComplaint;

}

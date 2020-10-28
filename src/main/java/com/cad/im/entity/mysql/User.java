package com.cad.im.entity.mysql;

import com.cad.im.entity.profile.BasicInformation;
import com.cad.im.entity.profile.ChiefComplaint;
import lombok.Data;

import javax.persistence.*;
import java.util.Base64;


@Data
@Entity
public class User {
    @Id
    private String userId; // 主键，微信openId
    private String nickName; // 用户昵称
    private String avatarUrl; // 头像
    private String identity; // 身份--'patient','doctor'
    // 微信登录
//    private String openId;
    private String session;

    public User(){}

    public User(String openId, String identity){
        this.userId = openId;
        this.identity = identity;
    }

    public void setNickName(String nickName){
        this.nickName = new String(Base64.getEncoder().encode(nickName.getBytes()));
    }

    public String getNickName(){
        return new String(Base64.getDecoder().decode(this.nickName.getBytes()));
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

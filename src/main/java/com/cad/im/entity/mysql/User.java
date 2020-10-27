package com.cad.im.entity.mysql;

import com.cad.im.entity.profile.BasicInformation;
import com.cad.im.entity.profile.ChiefComplaint;
import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String user_id; // 自增主键
    private String nick_name; // 用户昵称
    private String avatar_url; // 头像
    private String ident; // 身份--'patient','doctor'
    // 微信登录
    private String open_id;
    private String session;

    public User(){}

    public User(String nickName, String avatarUrl){
        this.nick_name = nickName;
        this.avatar_url = avatarUrl;
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

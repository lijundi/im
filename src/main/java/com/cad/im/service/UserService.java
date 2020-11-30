package com.cad.im.service;

import com.alibaba.fastjson.JSONObject;
import com.cad.im.bean.Constant;
import com.cad.im.entity.http.LoginInfo;
import com.cad.im.entity.mysql.User;
import com.cad.im.entity.mysql.UserRelation;
import com.cad.im.repository.UserRelationRepository;
import com.cad.im.repository.UserRepository;
import com.cad.im.util.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final Constant constant;
    @Autowired
    UserRelationRepository userRelationRepository;

    public UserService(UserRepository userRepository, Constant constant) {
        this.userRepository = userRepository;
        this.constant = constant;
    }

    public Boolean isUserExist(String userId){
        return userRepository.findByUserId(userId).size() != 0;
    }

    public User login(LoginInfo loginInfo) throws Exception {
        // 获取openId和session
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid="+ constant.getAppId() +"&secret="+ constant.getAppSecret() +"&js_code="+ loginInfo.getCode() +"&grant_type=authorization_code";
        JSONObject loginJo = HttpUtil.getJson(url).getBody();
        if(loginJo==null){
            throw new Exception("微信接口出错");
        }
        String openId = loginJo.getString("openid");
        LOGGER.info(openId + "---" + loginInfo.getNickName() +" login!");
        // 检查是否为第一次登录
        List<User> userList = userRepository.findByUserId(openId);
        User user;
        if(userList.size()==0){
            user = new User(openId, "patient");
            //每次患者用户创建，就把所有医生用户变为好友
            List<User> list = userRepository.findByIdentity("doctor");
            for(User tmp : list){
                UserRelation userRelation = new UserRelation(openId, tmp.getUserId(), tmp.getNickName(), true);
                UserRelation friendRelation = new UserRelation(tmp.getUserId(), openId, loginInfo.getNickName(), true);
                userRelationRepository.save(userRelation);
                userRelationRepository.save(friendRelation);
            }
        }else{
            user = userList.get(0);
        }
        user.setNickName(loginInfo.getNickName());
        user.setSession(loginJo.getString("session_key"));
        user.setAvatarUrl(loginInfo.getAvatarUrl());

        return userRepository.save(user);
    }

    public void changeIdentity(String identity, String userId){
        User user = userRepository.findByUserId(userId).get(0);
        user.setIdentity(identity);
        userRepository.save(user);
    }
}

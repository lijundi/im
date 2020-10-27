package com.cad.im.service;

import com.cad.im.entity.http.LoginInfo;
import com.cad.im.entity.mysql.User;
import com.cad.im.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public Boolean isUserExist(String userId){
        return userRepository.findById(userId).isPresent();
    }

    public User login(LoginInfo loginInfo){
        // 获取openId和session

        // 检查是否为第一次登录，目前根据nickName判断
        List<User> userList = userRepository.findByNickName(loginInfo.getNickName());
        if(userList.size()==0){
            User user = new User(loginInfo.getNickName(), loginInfo.getAvatarUrl());
            user.setIdent("patient");
            return userRepository.save(user);
        }else{
            // 更新头像等
            User user = userList.get(0);
            user.setAvatar_url(loginInfo.getAvatarUrl());
            return userRepository.save(user);
        }
    }

}

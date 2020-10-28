package com.cad.im.service;

import com.cad.im.entity.mysql.User;
import com.cad.im.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Name: com.cad.im.service.FriendService
 * @Date: 2020/10/25
 * @Auther: weiwending
 * @Description: 好友Service
 */

@Service
public class FriendService {
    @Autowired
    UserRepository userRepository;

    public List<User> getFriends(String identity){
        return userRepository.findByIdentity(identity);
    }

    public User getUserInfo(String userId){
        return userRepository.findByUserId(userId).get(0);
    }
}

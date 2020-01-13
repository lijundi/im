package com.cad.im.service;

import com.cad.im.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lijundi
 * @date 2020/1/9 15:52
 */
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public Boolean isUserExist(Integer userId){
        return userRepository.findById(userId).isPresent();
    }
}

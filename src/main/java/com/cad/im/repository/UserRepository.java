package com.cad.im.repository;

import com.cad.im.entity.mysql.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    public List<User> findByNickName(String nickName);

    public List<User> findByUserId(String userId);
}

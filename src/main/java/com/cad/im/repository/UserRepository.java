package com.cad.im.repository;

import com.cad.im.entity.mysql.User;
import com.cad.im.entity.mysql.UserRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    public List<User> findByNickName(String nickName);

    public List<User> findByUserId(String userId);

    public List<User> findByIdentity(String identity);

    //模糊查询好友
    @Query(value = "select * from user where user_id != ?1 and nick_name like CONCAT('%',?2,'%')", nativeQuery = true)
    List<User> findAllByUserIdAndNickName(String userId, String friendName);

}

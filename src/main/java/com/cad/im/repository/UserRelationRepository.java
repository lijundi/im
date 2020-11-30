package com.cad.im.repository;

import com.cad.im.entity.mysql.UserRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author lijundi
 * @date 2020/1/8 13:32
 */
@Repository
public interface UserRelationRepository extends JpaRepository<UserRelation, Long> {
    //删除好友
    @Modifying
    @Transactional(rollbackOn = Exception.class)
    @Query(value = "delete from user_relation where user_id = ?1 and friend_id = ?2", nativeQuery = true)
    void delByUserAndFriend(String userId, String friendId);

    //模糊查询好友
    @Query(value = "select * from user_relation where user_id = ?1 and friend_name like CONCAT('%',?2,'%') and status = true ", nativeQuery = true)
    List<UserRelation> findByUserAndFriend(String userId, String friendName);

    List<UserRelation> findByUserIdAndFriendId(String userId, String friendId);

    //建立好友关系
    @Modifying
    @Transactional(rollbackOn = Exception.class)
    @Query(value = "update user_relation set status = true where user_id = ?1 and friend_id = ?2", nativeQuery = true)
    void updateRelation(String userId, String friendId);

    //查询好友申请列表
    List<UserRelation> findByUserIdAndStatus(String userId, Boolean status);

}

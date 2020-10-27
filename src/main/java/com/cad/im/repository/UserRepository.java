package com.cad.im.repository;

import com.cad.im.entity.mysql.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query(value = "select * from user where nick_name = ?1", nativeQuery = true)
    public List<User> findByNickName(String nickName);

    @Query(value = "select * from user where ident = ?1", nativeQuery = true)
    public List<User> getByIdentity(String identity);

    @Query(value = "select * from user where user_id = ?1", nativeQuery = true)
    public User getUserInfo(String userId);
}

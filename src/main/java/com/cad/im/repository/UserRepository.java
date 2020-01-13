package com.cad.im.repository;

import com.cad.im.entity.mysql.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lijundi
 * @date 2020/1/8 13:31
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}

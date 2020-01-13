package com.cad.im.repository;

import com.cad.im.entity.mysql.UserRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lijundi
 * @date 2020/1/8 13:32
 */
@Repository
public interface UserRelationRepository extends JpaRepository<UserRelation, Long> {
}

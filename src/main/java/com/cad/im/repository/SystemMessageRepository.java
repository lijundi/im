package com.cad.im.repository;

import com.cad.im.entity.mysql.SystemMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemMessageRepository extends JpaRepository<SystemMessage, Long> {
}

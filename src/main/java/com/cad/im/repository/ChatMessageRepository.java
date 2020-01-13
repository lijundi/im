package com.cad.im.repository;

import com.cad.im.entity.mysql.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lijundi
 * @date 2020/1/8 13:31
 */
@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
}

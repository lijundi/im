package com.cad.im.repository;

import com.cad.im.entity.mysql.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    @Query(value = "select * from chat_message where (from_id = ?2 or to_id = ?2) and user_id = ?1", nativeQuery = true)
    public List<ChatMessage> getMessages(String userId, String friendId);

    @Query(value = "select * from chat_message where (from_id = ?2 or to_id = ?2) and user_id = ?1  and time_stamp <= ?3 ORDER BY time_stamp DESC limit 10", nativeQuery = true)
    public List<ChatMessage> getHistorys(String userId, String friendId, Date timeStamp);
}

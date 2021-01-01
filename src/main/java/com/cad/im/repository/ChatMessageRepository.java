package com.cad.im.repository;

import com.cad.im.entity.mysql.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    @Query(value = "select * from chat_message where to_id = ?1 and offline = 1", nativeQuery = true)
    public List<ChatMessage> getOfflines(String userId);

    @Query(value = "select * from chat_message where ((from_id = ?1 and to_id = ?2) or (from_id = ?2 and to_id = ?1))  and time_stamp < ?3 ORDER BY time_stamp DESC limit 10", nativeQuery = true)
    public List<ChatMessage> getHistorys(String userId, String friendId, Date timeStamp);

    @Query(value = "select type, content, time_stamp as timeStamp from chat_message  where chat_message.to_id = ?1 ", nativeQuery = true)
    List<Map<String, Object>> getSystemOffline(String userId);

    @Query(value = "select type, content, time_stamp as timeStamp from chat_message where  to_id = ?1 and time_stamp < ?2 ORDER BY time_stamp DESC limit 10", nativeQuery = true)
    public List<Map<String, Object>> getSystemHistory(String userId, Date timeStamp);
}

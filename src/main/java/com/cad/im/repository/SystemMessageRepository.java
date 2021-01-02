package com.cad.im.repository;

import com.cad.im.entity.mysql.SystemMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface SystemMessageRepository extends JpaRepository<SystemMessage, Long> {
    @Query(value = "select * from system_message  where to_id = ?1 and offline = 1", nativeQuery = true)
    List<SystemMessage> getSystemOffline(String userId);

    @Query(value = "select type, content, time_stamp as timeStamp from system_message where  to_id = ?1 and time_stamp < ?2 ORDER BY time_stamp DESC limit 10", nativeQuery = true)
    List<Map<String, Object>> getSystemHistory(String userId, Date timeStamp);
}

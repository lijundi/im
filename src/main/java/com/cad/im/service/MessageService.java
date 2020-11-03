package com.cad.im.service;

import com.cad.im.entity.mysql.ChatMessage;
import com.cad.im.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Name: com.cad.im.service.MessageService
 * @Date: 2020/10/26
 * @Auther: weiwending
 * @Description: 消息Service
 */

@Service
public class MessageService {
    @Autowired
    ChatMessageRepository chatMessageRepository;

    public List<ChatMessage> getMessages(String userId){
        List<ChatMessage> chatMessageList = chatMessageRepository.getOfflines(userId);
        // 修改离线消息标志位
        for(ChatMessage chatMessage: chatMessageList){
            chatMessage.setOffline(false);
            chatMessageRepository.save(chatMessage);
        }
        return chatMessageList;
    }

    public List<ChatMessage> getHistorys(String userId, String friendId, String timeStamp) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(timeStamp);
        return chatMessageRepository.getHistorys(userId, friendId, date);
    }
}

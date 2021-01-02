package com.cad.im.service;

import com.alibaba.fastjson.JSONObject;
import com.cad.im.entity.mysql.ChatMessage;
import com.cad.im.entity.mysql.SystemMessage;
import com.cad.im.repository.ChatMessageRepository;
import com.cad.im.repository.SystemMessageRepository;
import com.cad.im.util.Result;
import com.cad.im.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    @Autowired
    SimpMessagingTemplate SMT;
    @Autowired
    SystemMessageRepository systemMessageRepository;

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

    public List<Map<String, Object>> getSystemOffline(String userId){
        return chatMessageRepository.getSystemOffline(userId);
    }

    public List<Map<String, Object>> getSystemHistory(String userId, String timeStamp) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(timeStamp);
        return chatMessageRepository.getSystemHistory(userId, date);
    }

    // 转发系统消息
    public void forwardSystemMessage(SystemMessage systemMessage, String toId) {
        SMT.convertAndSendToUser(toId, "/topic/systemChat", systemMessage);
    }

    public Result addMessages(SystemMessage systemMessage){
        systemMessageRepository.save(systemMessage);
        return Result.success();
    }


}

package com.cad.im.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cad.im.entity.mysql.ChatMessage;
import com.cad.im.entity.websocket.WsChatMessage;
import com.cad.im.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * @author lijundi
 * @date 2020/1/8 20:00
 */
@Service
public class ChatService {
    @Autowired
    SimpMessagingTemplate SMT;
    @Autowired
    SessionHandler sessionHandler;
    @Autowired
    ChatMessageRepository chatMessageRepository;


    // 存储--效率低
    public void storeChatMessage(WsChatMessage wsChatMessage) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setFromId(wsChatMessage.getFromId());
        chatMessage.setToId(wsChatMessage.getToId());
        chatMessage.setContent(wsChatMessage.getContent());
        chatMessage.setType(wsChatMessage.getType());
        chatMessage.setTimeStamp(wsChatMessage.getTimeStamp());
        chatMessage.setOffline(!sessionHandler.isOnline(wsChatMessage.getToId()));
        chatMessageRepository.saveAndFlush(chatMessage);
    }

    // 转发
    public void forwardMessage(WsChatMessage wsChatMessage) {
        SMT.convertAndSendToUser(wsChatMessage.getToId(), "/topic/chat", wsChatMessage);
    }

    // 转发机器人消息
    public void forwardRobotMessage(JSONObject message, String toId) {
        SMT.convertAndSendToUser(toId, "/topic/robotChat", message);
    }

}

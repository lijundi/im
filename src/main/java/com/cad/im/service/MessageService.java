package com.cad.im.service;

import com.alibaba.fastjson.JSONObject;
import com.cad.im.entity.mysql.ChatMessage;
import com.cad.im.entity.mysql.SystemMessage;
import com.cad.im.entity.websocket.WsSystemMessage;
import com.cad.im.repository.ChatMessageRepository;
import com.cad.im.repository.SystemMessageRepository;
import com.cad.im.util.Result;
import com.cad.im.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    @Autowired
    SessionHandler sessionHandler;

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

    public List<WsSystemMessage> getSystemOffline(String userId){
        List<SystemMessage> systemMessageList = systemMessageRepository.getSystemOffline(userId);
        List<WsSystemMessage> wsSystemMessageList = new ArrayList<>();
        // 修改离线消息标志位
        for(SystemMessage systemMessage: systemMessageList){
            systemMessage.setOffline(false);
            systemMessageRepository.save(systemMessage);
            // 封装返回格式
            WsSystemMessage wsSystemMessage = new WsSystemMessage(systemMessage.getType(), systemMessage.getContent(), systemMessage.getTimeStamp());
            wsSystemMessageList.add(wsSystemMessage);
        }
        return wsSystemMessageList;
    }

    public List<WsSystemMessage> getSystemHistory(String userId, String timeStamp) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(timeStamp);
        List<SystemMessage> systemMessageList = systemMessageRepository.getSystemHistory(userId, date);
        List<WsSystemMessage> wsSystemMessageList = new ArrayList<>();
        for(SystemMessage systemMessage : systemMessageList){
            WsSystemMessage wsSystemMessage = new WsSystemMessage(systemMessage.getType(), systemMessage.getContent(), systemMessage.getTimeStamp());
            wsSystemMessageList.add(wsSystemMessage);
        }
        return wsSystemMessageList;
    }

    // 转发系统消息
    public void forwardSystemMessage(WsSystemMessage wsSystemMessage, String toId) {
        SMT.convertAndSendToUser(toId, "/topic/systemChat", wsSystemMessage);
    }

    public void storeSystemMessage(WsSystemMessage wsSystemMessage, String toId){
        SystemMessage systemMessage = new SystemMessage();
        systemMessage.setContent(wsSystemMessage.getContent());
        systemMessage.setTimeStamp(wsSystemMessage.getTimeStamp());
        systemMessage.setType(wsSystemMessage.getType());
        systemMessage.setToId(toId);
        systemMessage.setOffline(!sessionHandler.isOnline(toId));
        systemMessageRepository.saveAndFlush(systemMessage);
    }


}

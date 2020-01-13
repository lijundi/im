package com.cad.im.controrller;

import com.alibaba.fastjson.JSONObject;
import com.cad.im.entity.websocket.WsChatMessage;
import com.cad.im.service.ChatService;
import com.cad.im.service.SessionHandler;
import com.cad.im.service.UserService;
import com.cad.im.util.Result;
import com.cad.im.util.ResultCode;
import com.cad.im.websocket.MyHandShakeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;

import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lijundi
 * @date 2020/1/8 15:58
 */
@RestController
public class WebSocketController {
    @Autowired
    SessionHandler sessionHandler;
    @Autowired
    ChatService chatService;
    @Autowired
    UserService userService;


    @MessageMapping("/chat")
    @SendToUser("topic/result")
    public Result chat(@RequestBody WsChatMessage wsChatMessage) {
        try {
            if (!userService.isUserExist(wsChatMessage.getTo_id())) // 接收方id不存在
                throw new Exception();
            chatService.storeChatMessage(wsChatMessage);
            if (sessionHandler.isOnline(String.valueOf(wsChatMessage.getTo_id())))
                chatService.forwardMessage(wsChatMessage);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("message_id", wsChatMessage.getMessage_id());
            return Result.success(jsonObject);
        } catch (Exception ex) {
            return Result.failure(ResultCode.FAILURE);
        }
    }
}

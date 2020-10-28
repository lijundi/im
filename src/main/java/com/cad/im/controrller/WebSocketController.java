package com.cad.im.controrller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cad.im.entity.websocket.WsChatMessage;
import com.cad.im.service.ChatService;
import com.cad.im.service.RobotService;
import com.cad.im.service.SessionHandler;
import com.cad.im.service.UserService;
import com.cad.im.util.Result;
import com.cad.im.util.ResultCode;
import com.cad.im.websocket.MyHandShakeInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.messaging.handler.annotation.MessageMapping;

import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@RestController
public class WebSocketController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    @Autowired
    SessionHandler sessionHandler;
    @Autowired
    ChatService chatService;
    @Autowired
    UserService userService;
    @Autowired
    RobotService robotService;


    @MessageMapping("/chat")
    @SendToUser("topic/result")
    public Result chat(@RequestBody WsChatMessage wsChatMessage) {
        try {
            if (!userService.isUserExist(wsChatMessage.getToId())) // 接收方id不存在
                throw new Exception("接收方Id不存在");
            chatService.storeChatMessage(wsChatMessage);
            if (sessionHandler.isOnline(wsChatMessage.getToId()))
                chatService.forwardMessage(wsChatMessage);
            return Result.success();
        } catch (Exception ex) {
            LOGGER.error(ex.toString());
            return Result.failure(ResultCode.FAILURE);
        }
    }

    @MessageMapping("/robotChat")
    @SendToUser("topic/result")
    public Result robotChat(@RequestBody WsChatMessage wsChatMessage) {
        try {
            JSONObject[] msgObjects = robotService.sendMsgToRobot(wsChatMessage.getFromId(), wsChatMessage.getContent());
            if(msgObjects!=null){
                for (JSONObject msgObject : msgObjects) {
                    WsChatMessage wsChatMsg = new WsChatMessage("1",
                            wsChatMessage.getFromId(), "text", msgObject.getString("text"), new Date());
                    JSONObject message = new JSONObject();
                    message.put("wsChatMessage", wsChatMsg);
                    // 多选消息 or  表单消息
                    JSONArray buttons = msgObject.getJSONArray("buttons");
                    if (buttons != null) {
                        String type = buttons.getJSONObject(0).getString("type");
                        if(type != null){
                            message.put("inputs", type);
                        }else{
                            message.put("buttons", buttons);
                        }
                    }
                    chatService.forwardRobotMessage(message, wsChatMessage.getFromId());
                }
                return Result.success();
            }
            return Result.failure(ResultCode.FAILURE);
        } catch (Exception ex) {
            LOGGER.error(ex.toString());
            return Result.failure(ResultCode.FAILURE);
        }
    }
}

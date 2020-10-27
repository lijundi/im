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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.messaging.handler.annotation.MessageMapping;

import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@RestController
public class WebSocketController {
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
            if (!userService.isUserExist(wsChatMessage.getTo_id())) // 接收方id不存在
                throw new Exception();
            chatService.storeChatMessage(wsChatMessage);
            if (sessionHandler.isOnline(String.valueOf(wsChatMessage.getTo_id())))
                chatService.forwardMessage(wsChatMessage);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("message_id", wsChatMessage.getMessage_id());
            return Result.success(jsonObject);
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return Result.failure(ResultCode.FAILURE);
        }
    }

    @MessageMapping("/robotChat")
    @SendToUser("topic/result")
    public Result robotChat(@RequestBody WsChatMessage wsChatMessage) {
        try {
            JSONObject[] msgObjects = robotService.sendMsgToRobot(wsChatMessage.getFrom_id(), wsChatMessage.getContent());
            if(msgObjects!=null){
                for (JSONObject msgObject : msgObjects) {
                    WsChatMessage wsChatMsg = new WsChatMessage(wsChatMessage.getMessage_id() + 1, "1",
                            wsChatMessage.getFrom_id(), "text", msgObject.getString("text"), "");
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
                    chatService.forwardRobotMessage(message, wsChatMessage.getFrom_id());
                }
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("message_id", wsChatMessage.getMessage_id());
                return Result.success(jsonObject);
            }
            return Result.failure(ResultCode.FAILURE);
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return Result.failure(ResultCode.FAILURE);
        }
    }


}

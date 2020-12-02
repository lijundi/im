package com.cad.im.controrller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cad.im.entity.websocket.FriendMessage;
import com.cad.im.entity.websocket.WsChatMessage;
import com.cad.im.service.*;
import com.cad.im.util.Result;
import com.cad.im.util.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


@RestController
public class WebSocketController {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketController.class);
    @Autowired
    SessionHandler sessionHandler;
    @Autowired
    ChatService chatService;
    @Autowired
    UserService userService;
    @Autowired
    RobotService robotService;
    @Autowired
    FriendService friendService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public WebSocketController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }


    @MessageMapping("/chat")
    @SendToUser("topic/result")
    public Result chat(@RequestBody WsChatMessage wsChatMessage) {
        try {
            if (!userService.isUserExist(wsChatMessage.getToId())) // 接收方id不存在
            {
                throw new Exception("接收方Id不存在");
            }
            chatService.storeChatMessage(wsChatMessage);
            if (sessionHandler.isOnline(wsChatMessage.getToId())) {
                chatService.forwardMessage(wsChatMessage);
            }
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
//                    String type_string = msgObject.getString("type");
                    // 多选消息 or  表单消息
//                    JSONArray buttons = msgObject.getJSONArray("buttons");
//                    if (buttons != null) {
//                        String type = buttons.getJSONObject(0).getString("type");
//                        if(type != null){
//                            message.put("inputs", type);
//                        }else{
//                            message.put("buttons", buttons);
//                        }
//                    }
                    message = robotService.judgeType(message, msgObject);
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

//    //同意添加好友
//    @MessageMapping("/agree/{userId}")
//    @SendToUser("/friend/apply")
//    public Result agreeFriend(String userId, String friendId) {
//        try {
//            String message = "好友请求已通过";
//            //点对点通信
//            simpMessagingTemplate.convertAndSendToUser(friendId, "/friend/apply", message);
//            return friendService.agreeFriend(userId, friendId);
//        } catch (Exception ex) {
//            LOGGER.error(ex.toString());
//            return Result.failure(ResultCode.FAILURE);
//        }
//    }
//
//    //申请好友位
//    @MessageMapping("/apply/{userId}")
//    @SendToUser("/friend/agree")
//    public Result applyFriend(@RequestBody FriendMessage friendMessage) {
//        try {
//            String message = friendMessage.getFriendName()+"向您发起好友请求";
//            //点对点通信
//            simpMessagingTemplate.convertAndSendToUser(friendMessage.getFriendId(), "/friend/agree", message);
//            return friendService.applyFriend(friendMessage.getUserId(), friendMessage.getFriendId(), friendMessage.getUserName(), friendMessage.getFriendName());
//        } catch (Exception ex) {
//            LOGGER.error(ex.toString());
//            return Result.failure(ResultCode.FAILURE);
//        }
//    }
}

package com.cad.im.controrller;

import com.alibaba.fastjson.JSONObject;
import com.cad.im.entity.mysql.ChatMessage;
import com.cad.im.service.MessageService;
import com.cad.im.util.Result;
import com.cad.im.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Name: com.cad.im.controrller.MessageController
 * @Date: 2020/10/26
 * @Auther: weiwending
 * @Description: 消息管理
 */

@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    MessageService messageService;

    //获取离线消息
//    @GetMapping("/offline")
//    public Result getMessages(String userId) {
//        try {
//            List<ChatMessage> chatMessages = messageService.getMessages(userId);
//            List res = new ArrayList();
//            for (ChatMessage chatMessage : chatMessages) {
//                Map map = new HashMap();
//                map.put("fromId", chatMessage.getFromId());
//                map.put("toId", chatMessage.getToId());
//                map.put("type", chatMessage.getType());
//                map.put("content", chatMessage.getContent());
//                map.put("timeStamp", chatMessage.getTimeStamp());
//                res.add(map);
//            }
//            return Result.success(res);
//        } catch (Exception ex) {
//            return Result.failure(ResultCode.FAILURE, ex.toString());
//        }
//    }

    //获取历史消息
    @GetMapping("/history")
    public Result getHistorys(String userId, String friendId, String timeStamp) {
        try {
            List<ChatMessage> chatMessages = messageService.getHistorys(userId, friendId, timeStamp);
            List res = new ArrayList();
            for (ChatMessage chatMessage : chatMessages) {
                Map map = new HashMap();
                map.put("fromId", chatMessage.getFromId());
                map.put("toId", chatMessage.getToId());
                map.put("type", chatMessage.getType());
                map.put("content", chatMessage.getContent());
                map.put("timeStamp", chatMessage.getTimeStamp());
                res.add(map);
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("data", res);
            return Result.success(jsonObject);
        } catch (Exception ex) {
            return Result.failure(ResultCode.FAILURE, ex.toString());
        }
    }
}

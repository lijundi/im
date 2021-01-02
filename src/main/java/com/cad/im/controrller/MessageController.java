package com.cad.im.controrller;

import com.alibaba.fastjson.JSONObject;
import com.cad.im.entity.mysql.ChatMessage;
import com.cad.im.entity.mysql.SystemMessage;
import com.cad.im.entity.websocket.WsSystemMessage;
import com.cad.im.service.MessageService;
import com.cad.im.util.Result;
import com.cad.im.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    @GetMapping("/offline")
    public Result getMessages(String userId) {
        try {
            List<ChatMessage> offlines = messageService.getMessages(userId);
            return Result.success(offlines);
        } catch (Exception ex) {
            return Result.failure(ResultCode.FAILURE, ex.toString());
        }
    }

    //获取历史消息
    @GetMapping("/history")
    public Result getHistorys(String userId, String friendId, String timeStamp) {
        try {
            List<ChatMessage> historys = messageService.getHistorys(userId, friendId, timeStamp);
            return Result.success(historys);
        } catch (Exception ex) {
            return Result.failure(ResultCode.FAILURE, ex.toString());
        }
    }

    //离线系统消息
    @GetMapping("/offline/system")
    public Result getSystemOffline(String userId){
        try{
            return Result.success(messageService.getSystemOffline(userId));
        }catch (Exception ex){
            return Result.failure(ResultCode.FAILURE, ex.toString());
        }
    }

    //历史系统消息
    @GetMapping("/history/system")
    public Result getSystemHistory(String userId, String timeStamp){
        try{
            return Result.success(messageService.getSystemHistory(userId, timeStamp));
        }catch (Exception ex){
            return Result.failure(ResultCode.FAILURE, ex.toString());
        }
    }

    //测试系统消息
    @GetMapping("/system/test")
    public Result addMessages(String type, String toId){
        WsSystemMessage systemMessage;
        switch (type){
            case "bp":
                systemMessage = new WsSystemMessage(type, "血压监测", new Date());
                break;
            case "friend":
                systemMessage = new WsSystemMessage(type, "好友请求", new Date());
                break;
            default:
                systemMessage = new WsSystemMessage(type, "靶器官及心血管病症状评估", new Date());
        }
        messageService.forwardSystemMessage(systemMessage, toId);
        return Result.success();
    }
}

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
}

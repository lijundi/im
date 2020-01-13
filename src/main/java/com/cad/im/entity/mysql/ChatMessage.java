package com.cad.im.entity.mysql;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author lijundi
 * @date 2020/1/8 12:23
 */
@Data
@Entity
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatMessage_id;
    private Integer from_id;
    private Integer to_id;
    private String type;
    private String content;
    private String timeStamp;
    private Boolean offline; // 离线消息标记

    public ChatMessage(){}

}

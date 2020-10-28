package com.cad.im.entity.mysql;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fromId;
    private String toId;
    @Column(length = 50)
    private String type; //text,image
    private String content;
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeStamp;
    private Boolean offline; // 离线消息标记

    public ChatMessage(){}

}

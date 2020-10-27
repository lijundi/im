package com.cad.im.entity.mysql;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
@Table(name="message")
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String from_id;
    private String to_id;
    @Column(length = 50)
    private String type; //text,image
    private String content;
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeStamp;
    private Boolean offline; // 离线消息标记
    private String user_id;

    public ChatMessage(){}

}

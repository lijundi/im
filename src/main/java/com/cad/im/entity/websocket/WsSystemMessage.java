package com.cad.im.entity.websocket;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;


@Data
public class WsSystemMessage {
    private String type;
    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date timeStamp;

    public WsSystemMessage(){}

    public WsSystemMessage(String type, String content, Date timeStamp){
        this.type = type;
        this.content = content;
        this.timeStamp = timeStamp;
    }
}

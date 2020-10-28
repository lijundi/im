package com.cad.im.entity.websocket;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author lijundi
 * @date 2020/1/9 13:57
 */
@Data
public class WsChatMessage {
    private String fromId;
    private String toId;
    private String type;
    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date timeStamp;

    public WsChatMessage(){}

    public WsChatMessage(String fromId, String toId, String type, String content, Date timeStamp){
        this.fromId = fromId;
        this.toId = toId;
        this.type = type;
        this.content = content;
        this.timeStamp = timeStamp;
    }
}

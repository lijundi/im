package com.cad.im.entity.websocket;

import lombok.Data;

/**
 * @author lijundi
 * @date 2020/1/9 13:57
 */
@Data
public class WsChatMessage {
    private Integer message_id;
    private Integer from_id;
    private Integer to_id;
    private String type;
    private String content;
    private String timeStamp;

    public WsChatMessage(){}

    public WsChatMessage(Integer message_id, Integer from_id, Integer to_id, String type, String content, String timeStamp){
        this.message_id = message_id;
        this.from_id = from_id;
        this.to_id = to_id;
        this.type = type;
        this.content = content;
        this.timeStamp = timeStamp;
    }
}

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
}

package com.cad.im.websocket;

import com.cad.im.service.SessionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.messaging.SubProtocolWebSocketHandler;

import java.util.Objects;

/**
 * @author lijundi
 * @date 2020/1/8 17:17
 */
public class MyWebSocketHandler extends SubProtocolWebSocketHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyWebSocketHandler.class);
    @Autowired
    private SessionHandler sessionHandler;

    public MyWebSocketHandler(MessageChannel clientInboundChannel, SubscribableChannel clientOutboundChannel) {
        super(clientInboundChannel, clientOutboundChannel);
    }

    // 建立连接 用户在线
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        LOGGER.info("New websocket connection: " + Objects.requireNonNull(session.getPrincipal()).getName() + " was connected.");
        sessionHandler.register(session);
        super.afterConnectionEstablished(session);
    }

    // 关闭连接 用户下线
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        LOGGER.info("websocket closed: " + Objects.requireNonNull(session.getPrincipal()).getName() + " was closed.");
        sessionHandler.remove(session);
        super.afterConnectionClosed(session,closeStatus);
    }

}

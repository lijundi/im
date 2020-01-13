package com.cad.im.service;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lijundi
 * @date 2020/1/8 17:12
 */
// 用户状态管理
@Service
public class SessionHandler {
    private final Map<String, WebSocketSession> sessionMap = new ConcurrentHashMap<>();

    public SessionHandler() {

    }

    public void register(WebSocketSession session) {
        sessionMap.put(Objects.requireNonNull(session.getPrincipal()).getName(), session);
    }

    public void remove(WebSocketSession session) {
        sessionMap.remove(Objects.requireNonNull(session.getPrincipal()).getName());
    }

    public boolean isOnline(String openid){
        return sessionMap.get(openid)!=null;
    }

}

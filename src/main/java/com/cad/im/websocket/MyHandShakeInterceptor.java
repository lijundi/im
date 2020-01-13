package com.cad.im.websocket;

import com.cad.im.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

/**
 * @author lijundi
 * @date 2020/1/9 16:12
 */
// 拦截器，用于身份验证
@Component
public class MyHandShakeInterceptor implements HandshakeInterceptor {
    private static UserService userService;

    @Autowired
    public void setUserService(UserService userService){
        MyHandShakeInterceptor.userService = userService;
    }

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) throws Exception {
        // 存在即登录成功
        String userId = ((ServletServerHttpRequest) request).getServletRequest().getParameter("userId");
        return userService.isUserExist(Integer.parseInt(userId));
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex) {

    }
}

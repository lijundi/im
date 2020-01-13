package com.cad.im.websocket;

import java.security.Principal;

/**
 * @author lijundi
 * @date 2020/1/8 15:48
 */
// 自定义权限验证类
public class MyPrincipal implements Principal {

    private String name;

    public MyPrincipal(String name){
        this.name = name;
    }

    public MyPrincipal() {
    }

    @Override
    public String getName() {
        return this.name;
    }
}

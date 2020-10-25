package com.cad.im.entity.http;

import lombok.Data;

@Data
public class LoginInfo {
    private String code;
    private String nickName;
    private String avatarUrl;

    public LoginInfo(String code, String nickName, String avatarUrl){
        this.code = code;
        this.nickName = nickName;
        this.avatarUrl = avatarUrl;
    }
}

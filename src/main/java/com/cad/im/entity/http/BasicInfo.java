package com.cad.im.entity.http;

import lombok.Data;

@Data
public class BasicInfo {
    private Integer userId;
    private String name;
    private Integer age;
    private String gender; // 性别
    private String occupation; // 职业

    public BasicInfo (Integer userId, String name, Integer age, String gender, String occupation){
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.occupation = occupation;
    }
}

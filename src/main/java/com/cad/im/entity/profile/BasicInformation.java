package com.cad.im.entity.profile;

import com.cad.im.entity.mysql.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class BasicInformation {
    @Id
    @Column(length = 50)
    private String userId;

    private String name;
    private Integer age;
    @Column(length = 10)
    private String gender; // 性别
    private String occupation; // 职业


    public BasicInformation(){}
}

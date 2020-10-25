package com.cad.im.entity.profile;

import com.cad.im.entity.mysql.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class BasicInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 自增主键

    private String name;
    private Integer age;
    @Column(length = 10)
    private String gender; // 性别
    private String occupation; // 职业

    private Integer userId;

    public BasicInformation(){}
}

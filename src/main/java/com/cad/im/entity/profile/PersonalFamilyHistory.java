package com.cad.im.entity.profile;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class PersonalFamilyHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 自增主键

    private Integer smoke_year; // 吸烟年数
    private Integer smoke_num; // 每天吸烟支数
    private Integer high_drink_year; // 饮高度酒年数
    private Integer high_drink_quantity; // 每天饮高度酒两数
    private Integer week_sports_num; // 每周运动次数
    @Column(length = 10)
    private String snoring_night; // 有、无 夜间打鼾

    private Integer userId;
}

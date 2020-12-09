package com.cad.im.entity.profile;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class TocdaHaveSymptom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 自增主键

    private String dis_onset; // 部位
    private String dis_property; // 性质
    private String dis_degree; // 程度
    private String dis_incentive; // 诱发方式
    private String dis_relief; // 缓解方式
    private Integer dis_duration; // 每次发作的持续时间
    private String time_unit; // 时间单位

    private String userId;
}

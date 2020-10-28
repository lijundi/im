package com.cad.im.entity.profile;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class TargetOrganCardiovascularDiseaseAssessment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 自增主键

    private Integer tocda_climb_floor_num; // 爬楼层数
    @Column(length = 10)
    private String tocda_oppressive_wake; // 有、无 夜间憋醒
    private Integer tocda_night_urine_num; // 睡着后夜尿次数
    private Integer tocda_day_urine_num; // 白天一般尿次数

    @Column(length = 50)
    private String userId;
}

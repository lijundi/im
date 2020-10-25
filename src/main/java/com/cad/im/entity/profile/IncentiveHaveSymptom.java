package com.cad.im.entity.profile;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class IncentiveHaveSymptom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 自增主键

    private String dis_onset; // 部位
    private String dis_property; // 性质
    private String dis_degree; // 程度
    private String incentive_process_detail; // 若为""表示无处理

    private Integer userId;
}

package com.cad.im.entity.profile;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class AntihypertensiveDrugsEffect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 自增主键

    private String anti_drug_name; // 药物名称
    private Integer anti_drug_year; // 服用年份
    private Integer anti_drug_dose; // 每日服用剂量
    private String anti_drug_before_blood_pressure; // 服用前血压值
    private String anti_drug_after_blood_pressure; // 服用后血压值

    private Integer userId;
}

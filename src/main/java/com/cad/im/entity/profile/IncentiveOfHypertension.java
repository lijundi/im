package com.cad.im.entity.profile;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class IncentiveOfHypertension {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 自增主键

    private String incentiveBloodPressure; // 血压值，格式为xxx/xxxmmHg
    private String incentive_reason; // 原因
    private String incentive_location; // 地点

    private Integer userId;
}
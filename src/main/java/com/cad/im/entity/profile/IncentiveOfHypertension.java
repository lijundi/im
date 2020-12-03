package com.cad.im.entity.profile;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class IncentiveOfHypertension {
    @Id
    @Column(length = 50)
    private String userId;

    private String incentiveBloodPressure; // 血压值，格式为xxx/xxxmmHg
    private String incentive_reason; // 原因
    private String incentive_location; // 地点


}

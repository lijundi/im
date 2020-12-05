package com.cad.im.entity.profile;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class SecondaryHypertension {
    @Id
    @Column(length = 50)
    private String userId;

    @Column(length = 10)
    private String secondary_hypt_fever_cold; // 有、无 发热、感冒
    @Column(length = 10)
    private String secondary_hypt_edema_oliguria; // 浮肿、少尿
    @Column(length = 10)
    private String secondary_hypt_leg_weak; // 腿软、乏力
    @Column(length = 10)
    private String secondary_hypt_fear_heat_panic; // 怕热、心慌症状
    private Integer secondary_hypt_night_urine_num; // 睡着后夜尿次数
    private Integer secondary_hypt_day_urine_num; // 白天一般尿次数


}

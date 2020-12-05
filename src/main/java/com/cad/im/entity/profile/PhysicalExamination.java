package com.cad.im.entity.profile;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class PhysicalExamination {
    @Id
    @Column(length = 50)
    private String userId;

    private String blood_pressure; // 血压值
    private Integer heart_rate; // 心率
    private Float height; // 身高
    private Float weight; // 体重
    private Float waistline; // 腰围
    @Column(length = 10)
    private String edema_both_lower_limbs; // 有、无 双下肢浮肿


}

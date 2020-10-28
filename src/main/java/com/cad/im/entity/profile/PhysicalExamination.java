package com.cad.im.entity.profile;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class PhysicalExamination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 自增主键

    private String blood_pressure; // 血压值
    private Integer heart_rate; // 心率
    private Integer height; // 身高
    private Integer weight; // 体重
    private Integer waistline; // 腰围
    @Column(length = 10)
    private String edema_both_lower_limbs; // 有、无 双下肢浮肿

    @Column(length = 50)
    private String userId;

}

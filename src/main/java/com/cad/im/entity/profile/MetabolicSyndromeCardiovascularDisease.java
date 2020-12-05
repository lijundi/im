package com.cad.im.entity.profile;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class MetabolicSyndromeCardiovascularDisease {
    @Id
    @Column(length = 50)
    private String userId;

    @Column(length = 10)
    private String diabetes; // 有、无 糖尿病
    @Column(length = 10)
    private String dyslipidemia; // 有、无 血脂异常
    @Column(length = 10)
    private String coronary_heart_disease; // 有、无 冠心病
    @Column(length = 10)
    private String apoplexy; // 有、无 脑卒中
    @Column(length = 10)
    private String peripheral_vascular_disease; // 有、无 外周血管疾病
    @Column(length = 10)
    private String retinopathy; // 有、无 视网膜病变

}

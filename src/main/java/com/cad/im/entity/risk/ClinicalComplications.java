package com.cad.im.entity.risk;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author lijundi
 * @date 2020/11/13 17:03
 */
@Data
@Entity
public class ClinicalComplications {
    @Id
    @Column(length = 50)
    private String userId; // 主键，用户ID

    @Column(length = 10)
    private String cvd; // 脑血管疾病--是/否/未知
    @Column(length = 10)
    private String chd; // 心脏疾病--是/否/未知
    @Column(length = 10)
    private String ckd; // 肾脏疾病--是/否/未知
    @Column(length = 10)
    private String pvd; // 外周血管疾病--是/否/未知
    @Column(length = 10)
    private String retionpathy; // 视网膜病变--是/否/未知
    @Column(length = 10)
    private String diabetes; // 糖尿病--有/无/未知
}

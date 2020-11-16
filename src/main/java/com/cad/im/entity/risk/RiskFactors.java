package com.cad.im.entity.risk;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.criteria.CriteriaBuilder;

/**
 * @author lijundi
 * @date 2020/11/13 15:48
 */
@Data
@Entity
public class RiskFactors {
    @Id
    @Column(length = 50)
    private String userId; // 主键，用户ID

    @Column(length = 10)
    private String gender; // 性别--男/女
    private Integer age; // 年龄
    @Column(length = 20)
    private String smoke; // 吸烟情况--吸烟/被动吸烟/不吸烟
    @Column(length = 10)
    private String igt; // 糖耐量受损--是/否/未知
    private Float h2Bg; // 2h血糖含量
    private Float fasting_bg; // 空腹血糖含量
    @Column(length = 10)
    private String dyslipidemia; // 血脂异常--是/否/未知
    @Column(length = 10)
    private String family_history; // 早发心血管病家族史--是/否/未知
    @Column(length = 10)
    private String abdominal_obesity; // 腹型肥胖--是/否/未知
    private Float waist; // 腰围
    private Float bmi; // BMI
    private Float cysteine; // 半胱氨酸含量
    private Integer rf_num; // 危险因素数量
}

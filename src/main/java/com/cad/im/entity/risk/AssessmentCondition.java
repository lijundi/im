package com.cad.im.entity.risk;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author lijundi
 * @date 2020/11/18 21:07
 */
@Data
@Entity
public class AssessmentCondition {
    @Id
    @Column(length = 50)
    private String userId; // 主键，用户ID

    private Integer sbp; // 高压、收缩压
    private Integer dbp; // 低压、舒张压

    @Column(length = 10)
    private String gender; // 性别--男/女
    private Integer age; // 年龄
    @Column(length = 20)
    private String smoke; // 吸烟情况--吸烟/被动吸烟/不吸烟
    private Float h2Bg; // 2h血糖含量
    private Float fasting_bg; // 空腹血糖含量
    @Column(length = 50)
    private String dyslipidemia; // 血脂情况：是/否
    private Float tc; // 总胆固醇含量
    private Float ldl_c; // 低密度脂蛋白胆固醇含量
    private Float hdl_c; // 高密度脂蛋白胆固醇含量
    @Column(length = 10)
    private String cvd_family_history; // 早发心血管病家族史--是/否
    private Float waistline; // 腰围
    private Float bmi; // BMI
    private Float cysteine; // 半胱氨酸含量

    private Float sl_voltage; // 心电图Sokolow-Lyon电压
    private Float lvmi; // 左心室重量指数
    private Float imt; // 颈动脉内膜中层厚度
    private Float egfr; // 肾小球滤过率
    private Float serum_creatinine; // 血清肌酐
    private Float proteinuria; // 蛋白尿

    @Column(length = 10)
    private String cvd; // 脑血管疾病--是/否
    @Column(length = 10)
    private String chd; // 心脏疾病--是/否
    @Column(length = 10)
    private String ckd; // 肾脏疾病--是/否
    @Column(length = 10)
    private String pvd; // 外周血管疾病--是/否
    @Column(length = 10)
    private String retionpathy; // 视网膜病变--是/否
    @Column(length = 10)
    private String diabetes; // 糖尿病--是/否

    public AssessmentCondition(){}

    public AssessmentCondition(String userId){
        this.userId = userId;
        this.gender = "男";
        this.smoke = "不吸烟";
        this.dyslipidemia = "否";
        this.cvd_family_history = "否";
        this.cvd = "否";
        this.chd = "否";
        this.ckd = "否";
        this.pvd = "否";
        this.retionpathy = "否";
        this.diabetes = "否";
    }
}

package com.cad.im.entity.risk;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * @author lijundi
 * @date 2020/11/18 21:07
 */
@Data
@Entity
public class AssessmentResult {
    @Id
    @Column(length = 50)
    private String userId; // 主键，用户ID

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updateTime; // 更新时间

    @Column(length = 50)
    private String bpLevel; // 血压水平分类--正常/正常高值/1级高血压/2级高血压/3级高血压/单纯收缩期高血压

    @Column(length = 50)
    private String gender_age; // 性别年龄：男性>55岁/女性>65岁/否
    @Column(length = 50)
    private String smoke; // 吸烟情况：吸烟/被动吸烟/否
    @Column(length = 50)
    private String igt; // 糖耐量情况：糖耐量受损/否
    @Column(length = 50)
    private String dyslipidemia; // 血脂情况：血脂异常/否
    @Column(length = 50)
    private String cvd_family_history; // 早发心血管病家族史：早发心血管病家族史/否
    @Column(length = 50)
    private String abdominal_obesity; // 腹型肥胖：腹型肥胖/否
    @Column(length = 50)
    private String hhe; // 半胱氨酸：高同型半胱氨酸血症/否
    private Integer rfNum; // 危险因素数量

    @Column(length = 50)
    private String left_ventricular; // 左心室情况：左心室肥厚/否
    @Column(length = 50)
    private String usca; // 颈动脉超声：超声显示颈动脉粥样硬化/否
    @Column(length = 50)
    private String dgfr; // 肾小球滤过率：肾小球滤过率降低/否
    @Column(length = 50)
    private String isc; // 血清肌酐：血清肌酐升高/否
    @Column(length = 50)
    private String microalbuminuria; // 蛋白尿：微量白蛋白尿/否
    @Column(length = 50)
    private String targetOrganDamage; // 靶器官损害：是/否

    @Column(length = 50)
    private String cvd; // 脑血管疾病：脑血管疾病/否
    @Column(length = 50)
    private String chd; // 心脏疾病：心脏疾病/否
    @Column(length = 50)
    private String ckd; // 肾脏疾病：肾脏疾病/否
    @Column(length = 50)
    private String pvd; // 外周血管疾病：外周血管疾病/否
    @Column(length = 50)
    private String retinopathy; // 视网膜病变：视网膜病变/否
    @Column(length = 50)
    private String diabetes; // 糖尿病：糖尿病/否
    @Column(length = 50)
    private String clinicalComplications; // 临床并发症：是/否

    @Column(length = 50)
    private String riskLevel; // 危险等级：正常/低危/中危/高危/极高危

    public AssessmentResult(){}

    public AssessmentResult(String userId){
        this.userId = userId;
        this.bpLevel = "正常";
        this.rfNum = 0;
        this.riskLevel = "正常";
        this.gender_age = "否";
        this.smoke = "否";
        this.igt = "否";
        this.dyslipidemia = "否";
        this.cvd_family_history = "否";
        this.abdominal_obesity = "否";
        this.hhe = "否";
        this.left_ventricular = "否";
        this.usca = "否";
        this.dgfr = "否";
        this.isc = "否";
        this.microalbuminuria = "否";
        this.targetOrganDamage = "否";
        this.cvd = "否";
        this.chd = "否";
        this.ckd = "否";
        this.pvd = "否";
        this.retinopathy = "否";
        this.diabetes = "否";
        this.clinicalComplications = "否";
    }
}

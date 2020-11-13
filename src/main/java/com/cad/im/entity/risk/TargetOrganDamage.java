package com.cad.im.entity.risk;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author lijundi
 * @date 2020/11/13 16:07
 */
@Data
@Entity
public class TargetOrganDamage {
    @Id
    @Column(length = 50)
    private String userId; // 主键，用户ID

    @Column(length = 10)
    private String left_ventricular; // 左心室肥厚--是/否/未知
    @Column(length = 10)
    private String usca; // 超声显示颈动脉粥样硬化--是/否/未知
    @Column(length = 10)
    private String dgfr; // 肾小球滤过率降低--是/否/未知
    @Column(length = 10)
    private String isc; // 血清肌酐升高--是/否/未知
    @Column(length = 10)
    private String microalbuminuria; // 微量白蛋白尿--是/否/未知
}

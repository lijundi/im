package com.cad.im.entity.risk;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * @author lijundi
 * @date 2020/11/13 15:39
 */
@Data
@Entity
public class BpLevel {
    @Id
    @Column(length = 50)
    private String userId; // 主键，用户ID

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updateTime; // 更新时间

    private Integer sbp; // 高压、收缩压
    private Integer dbp; // 低压、舒张压

    @Column(length = 50)
    private String bp_level; // 血压水平分类--正常/正常高值/1级高血压/2级高血压/3级高血压/单纯收缩期高血压

    public BpLevel(String userId){
        this.userId = userId;
        this.sbp = null;
        this.dbp = null;
        this.bp_level = "正常";
    }
}

package com.cad.im.entity.mysql;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @Name: com.cad.im.entity.mysql.BpMonitor
 * @Date: 2020/12/30
 * @Auther: weiwending
 * @Description: 血压监测表
 */

@Data
@Entity
public class BpMonitor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private Integer sbp;
    private Integer dbp;
    private Integer heartRate;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    private String isExercise;
    private String exerciseMode;
    private String exerciseKm;
    private String exerciseMin;
    private String isGetUp;
    private Date bedTime;
    private Integer sleepHour;
    private Integer wakeUpNum;
    private String isDiscomfort;
    private String detailOfDiscomfort;
}

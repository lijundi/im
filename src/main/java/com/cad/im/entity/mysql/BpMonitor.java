package com.cad.im.entity.mysql;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
    private Integer heart_rate;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date create_time;
    private String is_exercise;
    private String exercise_mode;
    private String exercise_km;
    private String exercise_min;
    private String is_get_up;
    private Date bedtime;
    private Integer sleep_hour;
    private Integer wake_up_num;
    private String is_discomfort;
    private String detail_of_discomfort;
}

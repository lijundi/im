package com.cad.im.entity.mysql;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class MonitorTask {
    @Id
    @Column(length = 50)
    private String userId; // 主键，用户id

    // 频率：day3=每天8,14,20点 2week#day3=每天8,14,20点 4week#day3=每天8,14,20点
    // 2week#day2=2周每天8,20点 4week#day2=1个月每天8,20点
    // week1&day2=每周一天8,20点 year1&day1=每年一天8点
    private String frequency;
    private Integer num; // 任务次数
    private Integer remainingDays; // 剩余天数
    private String target; // 目标--bpCheck, nothing
}

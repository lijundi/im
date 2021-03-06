package com.cad.im.entity.profile;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class PreviousBloodPressureMeasurement {
    @Id
    @Column(length = 50)
    private String userId;

    private String primary_school_blood_pressure; // 小学时血压
    private String middle_school_blood_pressure; // 中学时血压
    private String working_blood_pressure; // 工作时血压
    private String premarital_check_blood_pressure; // 婚检时血压
    private String previous_accident_check_blood_pressure; // 既往偶测血压
    private String ordinary_blood_pressure; // 平时血压
    private String highest_blood_pressure; // 最高血压


}

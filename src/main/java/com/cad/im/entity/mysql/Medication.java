package com.cad.im.entity.mysql;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * @Name: com.cad.im.entity.mysql.Medication
 * @Date: 2020/10/26
 * @Auther: weiwending
 * @Description: 用药
 */

@Data
@Entity
@Table(name="medication")
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 50)
    private String userId;
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createTime;
    private String medicineName;
    private String startTime;
    private String endTime;
    private String frequency;
    private String medicineDose;
    private String beforeBp;
    private String afterBp;

}

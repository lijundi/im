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
    private String user_id;
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date create_time;
    private String medicine_name;
    private String start_time;
    private String end_time;
    private String frequency;
    private String medicine_dose;
    private String before_bp;
    private String after_bp;
}

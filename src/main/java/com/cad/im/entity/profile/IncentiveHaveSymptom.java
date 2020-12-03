package com.cad.im.entity.profile;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class IncentiveHaveSymptom {
    @Id
    @Column(length = 50)
    private String userId;

    private String dis_onset; // 部位
    private String dis_property; // 性质
    private String dis_degree; // 程度
    private String incentive_process_detail; // 若为""表示无处理


}

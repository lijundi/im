package com.cad.im.entity.profile;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class FamilyHaveHypertension {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 自增主键

    @Column(length = 20)
    private String family_who; // 患病人
    private Integer family_hypertension_age; // 发病年龄

    private String userId;
}

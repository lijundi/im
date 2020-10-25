package com.cad.im.entity.profile;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class LifeHabits {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 自增主键

    @Column(length = 10)
    private String life_salty_food; // 有、无 嗜好咸食
    @Column(length = 10)
    private String life_smoke; // 有、无 吸烟
    @Column(length = 10)
    private String life_drinking; // 有、无 酗酒
    @Column(length = 10)
    private String life_nervous; // 有、无 精神紧张
    @Column(length = 10)
    private String life_stay_up_late; // 有、无 经常熬夜

    private Integer userId;
}

package com.cad.im.entity.profile;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class GeneralSituationAssessment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 自增主键

    private Integer staple_food; // 每餐主食的份量
    @Column(length = 10)
    private String pickles_bean_paste; // 有、无 喜食咸菜、豆瓣酱等习惯
    private Integer sleep_hour; // 每晚睡眠时间
    @Column(length = 20)
    private String sleep_quality; // 好、一般、差 睡眠质量

    private Integer userId;
}

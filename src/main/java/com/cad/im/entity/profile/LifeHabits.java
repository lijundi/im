package com.cad.im.entity.profile;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class LifeHabits {
    @Id
    @Column(length = 50)
    private String userId;

    private Integer staple_food; // 每餐主食的份量
    @Column(length = 10)
    private String pickles_bean_paste; // 有、无 喜食咸菜、豆瓣酱等习惯
    private Integer sleep_hour; // 每晚睡眠时间
    @Column(length = 20)
    private String sleep_quality; // 好、一般、差 睡眠质量
    @Column(length = 10)
    private String life_stay_up_late; // 有、无 经常熬夜
    @Column(length = 10)
    private String snoring_night; // 有、无 夜间打鼾
    @Column(length = 10)
    private String life_nervous; // 有、无 精神紧张
    private Integer week_sports_num; // 每周运动次数
    @Column(length = 10)
    private String life_smoke; // 有、无 吸烟
    private Integer smoke_year; // 吸烟年数
    private Integer smoke_num; // 每天吸烟支数
    @Column(length = 10)
    private String high_drink; // 有、无 饮高度酒
    private Integer high_drink_year; // 饮高度酒年数
    private Integer high_drink_quantity; // 每天饮高度酒两数
    @Column(length = 10)
    private String life_drinking; // 有、无 酗酒

}

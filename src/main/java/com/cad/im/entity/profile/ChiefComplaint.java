package com.cad.im.entity.profile;

import com.cad.im.entity.mysql.User;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class ChiefComplaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 自增主键

    private Integer hypertensionYear; // 高血压患病年数

    private Integer userId;
//    @OneToOne(targetEntity = User.class,
//            cascade = {},
//            fetch = FetchType.LAZY)
//    private User user;
}

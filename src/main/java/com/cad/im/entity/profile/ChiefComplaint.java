package com.cad.im.entity.profile;

import com.cad.im.entity.mysql.User;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class ChiefComplaint {
    @Id
    @Column(length = 50)
    private String userId;

    private Integer hypertensionYear; // 高血压患病年数


//    @OneToOne(targetEntity = User.class,
//            cascade = {},
//            fetch = FetchType.LAZY)
//    private User user;
}

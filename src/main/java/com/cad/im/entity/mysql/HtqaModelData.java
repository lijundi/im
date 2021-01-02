package com.cad.im.entity.mysql;

import javax.persistence.Id;
import com.cad.im.entity.profile.BasicInformation;
import com.cad.im.entity.profile.ChiefComplaint;
import lombok.Data;

import javax.persistence.*;
import java.util.Base64;


@Data
@Entity
public class HtqaModelData {

    @Id
    @Column(length = 50)
    private String id;
    private String question;
    private String answer;
    private int directory_id;

    public HtqaModelData(){}

    public HtqaModelData(String id, String question, String answer, int directory_id) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.directory_id = directory_id;
    }
}

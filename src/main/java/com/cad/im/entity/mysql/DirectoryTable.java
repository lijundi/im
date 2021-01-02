package com.cad.im.entity.mysql;

import com.cad.im.entity.profile.BasicInformation;
import com.cad.im.entity.profile.ChiefComplaint;
import lombok.Data;

import javax.persistence.*;
import java.util.Base64;

@Data
@Entity
public class DirectoryTable {
    @Id
    @Column(length = 50)
    private int id;
    private int pid;
    private String node;

    public DirectoryTable() {}

    public DirectoryTable(int id, int pid, String node) {
        this.id = id;
        this.pid = pid;
        this.node = node;
    }

}

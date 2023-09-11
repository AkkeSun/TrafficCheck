package com.example.trafficcheck.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "TBL_API_INFO")
public class ApiInfo {

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "PATH")
    private String path;
    @Column(name = "REMARK")
    private String desc;
}

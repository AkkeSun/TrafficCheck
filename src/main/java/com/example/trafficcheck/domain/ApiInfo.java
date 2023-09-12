package com.example.trafficcheck.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;

/*
create table TBL_API_INFO
(
    ID       int auto_increment
        primary key,
    PATH     varchar(200)                        not null,
    REMARK   varchar(50)                         null comment '기타 설명',
    REG_DATE timestamp default CURRENT_TIMESTAMP not null comment '등록일시'
);
 */
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

package com.example.trafficcheck.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
create table TBL_TRAFFIC_CHECK
(
    ID             int auto_increment
        primary key,
    SERVER         varchar(10) not null comment 'API 호출된 서버',
    API_URL_ID     int         not null comment 'TBL_URL에 정의되어 있는 API ID',
    SUCCESS_COUNT  int         not null comment 'API 성공 횟수',
    FAIL_COUNT     int         not null comment 'API 실패 횟수',
    SLOW_API_COUNT int         not null comment '1초 이상(기준은 변경될 수 있음) 걸리는 API들에 대한 횟수',
    TOTAL_COUNT    int         not null comment 'API 총 호출 횟수',
    REG_DT         date        not null,
    REG_DATE       datetime    not null
);

create index TBL_TRAFFIC_CHECK_SERVICE_TYPE_API_URL_ID_REG_DT_index
    on TBL_TRAFFIC_CHECK (API_URL_ID, REG_DT);

create index TBL_TRAFFIC_CHECK_SERVICE_TYPE_REG_DT_index
    on TBL_TRAFFIC_CHECK (REG_DT);

create index TBL_TRAFFIC_CHECK_SERVICE_TYPE_SERVER_NO_API_URL_ID_REG_DT_index
    on TBL_TRAFFIC_CHECK (SERVER, API_URL_ID, REG_DT);

create index TBL_TRAFFIC_CHECK_SERVICE_TYPE_SERVER_NO_REG_DT_index
    on TBL_TRAFFIC_CHECK (SERVER, REG_DT);
 */
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TBL_TRAFFIC_CHECK")
public class TrafficCheck {

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "SERVER")
    private String server;

    @Column(name = "API_URL_ID")
    private int apiUriId;

    @Column(name = "SUCCESS_COUNT")
    private int successCount;

    @Column(name = "FAIL_COUNT")
    private int failCount;

    @Column(name = "SLOW_API_COUNT")
    private int slowApiCount;

    @Column(name = "TOTAL_COUNT")
    private int totalCount;

    @Column(name = "REG_DT")
    private LocalDate regDt;

    @Column(name = "REG_DATE")
    private LocalDateTime regDate;

}

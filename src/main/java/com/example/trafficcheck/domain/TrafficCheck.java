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

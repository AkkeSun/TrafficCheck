package com.example.trafficcheck.dto;

import com.example.trafficcheck.domain.TrafficCheck;
import com.example.trafficcheck.domain.ApiInfo;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TrafficCheckDTO {

    private String server;
    private LocalDate regDt;
    private LocalDateTime regDate;
    private int apiUrlId;
    private AtomicInteger successCount = new AtomicInteger(0);
    private AtomicInteger failCount = new AtomicInteger(0);
    private AtomicInteger slowApiCount = new AtomicInteger(0);
    private AtomicInteger totalCount = new AtomicInteger(0);

    public void incrementSuccessCount() {
        successCount.incrementAndGet();
    }

    public void incrementFailCount() {
        failCount.incrementAndGet();
    }

    public void incrementSlowApiCount() {
        slowApiCount.incrementAndGet();
    }

    public TrafficCheck toTrafficCheck() {
        return TrafficCheck.builder()
            .server(this.server)
            .apiUriId(this.apiUrlId)
            .successCount(this.successCount.get())
            .failCount(this.failCount.get())
            .totalCount(this.slowApiCount.get())
            .totalCount(this.successCount.get() + this.failCount.get() + this.slowApiCount.get())
            .regDt(LocalDate.now())
            .regDate(LocalDateTime.now())
            .build();
    }

    public TrafficCheckDTO(ApiInfo url, String server) {
        this.server = server;
        this.apiUrlId = url.getId();
    }
}
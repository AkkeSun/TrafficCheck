package com.example.trafficcheck.scheduler;

import com.example.trafficcheck.Application;
import com.example.trafficcheck.dao.TrafficCheckRepository;
import com.example.trafficcheck.domain.TrafficCheck;
import com.example.trafficcheck.dto.TrafficCheckDTO;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TrafficDataSavingScheduler {

    private final TrafficCheckRepository trafficCheckRepository;

    @Scheduled(cron = "0 0/1 * * * ?")  // 1분마다 저장
    public void saveTrafficData()  {

        log.info("save Traffic Data");
        ConcurrentHashMap<Integer, TrafficCheckDTO> trafficCheckConcurrentHashMap =
            Application.getTrafficCheckMap();

        if(!trafficCheckConcurrentHashMap.isEmpty()){
            List<TrafficCheck> trafficChecks = trafficCheckConcurrentHashMap.values()
                .stream()
                .map(TrafficCheckDTO::toTrafficCheck)
                .collect(Collectors.toList());
            trafficCheckRepository.saveAll(trafficChecks);
            Application.getTrafficCheckMap().clear();
        }
    }
}

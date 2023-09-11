package com.example.trafficcheck;

import com.example.trafficcheck.dto.TrafficCheckDTO;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class Application {

    private static ConcurrentHashMap<Integer, TrafficCheckDTO> trafficCheckMap =
        new ConcurrentHashMap<>();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    public static ConcurrentHashMap<Integer, TrafficCheckDTO> getTrafficCheckMap() {
        return trafficCheckMap;
    }


}

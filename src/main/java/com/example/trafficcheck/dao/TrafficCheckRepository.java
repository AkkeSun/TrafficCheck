package com.example.trafficcheck.dao;

import com.example.trafficcheck.domain.TrafficCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TrafficCheckRepository extends JpaRepository<TrafficCheck, Integer> {

}

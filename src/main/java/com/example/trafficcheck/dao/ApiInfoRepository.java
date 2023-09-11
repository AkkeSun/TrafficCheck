package com.example.trafficcheck.dao;

import com.example.trafficcheck.domain.ApiInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface ApiInfoRepository extends JpaRepository<ApiInfo, Integer> {
    ApiInfo findByPath(String path);
}

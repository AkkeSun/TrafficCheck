package com.example.trafficcheck.aop;

import com.example.trafficcheck.Application;
import com.example.trafficcheck.dao.ApiInfoRepository;
import com.example.trafficcheck.domain.ApiInfo;
import com.example.trafficcheck.dto.TrafficCheckDTO;
import java.net.InetAddress;
import java.net.UnknownHostException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class TrafficCheckAspect {

    private final ApiInfoRepository apiRepository;

    @Pointcut("execution(public * com.example.trafficcheck.controller.*.*(..))")
    private void pointcut() {
    }

    @Around("pointcut()")
    public Object trafficCheck(ProceedingJoinPoint joinPoint) throws Throwable {

        String apiUrl = getApiUrl(joinPoint);
        ApiInfo apiInfo = apiRepository.findByPath(apiUrl);

        if(ObjectUtils.isEmpty(apiInfo)){
            log.error("API NOT FOUND - {}", apiUrl);
            return joinPoint.proceed();
        } else {
            TrafficCheckDTO trafficCheckDTO = new TrafficCheckDTO(apiInfo, getHostName());
            Application.getTrafficCheckMap().putIfAbsent(apiInfo.getId(), trafficCheckDTO);

            trafficCheckDTO = Application.getTrafficCheckMap().get(apiInfo.getId());

            try {
                long begin = System.currentTimeMillis();
                Object proceed = joinPoint.proceed();
                long time = System.currentTimeMillis() - begin;
                log.info("[API CALL] {} -- {}", apiInfo.getPath(),  time);
                if (time < 1500L) {
                    trafficCheckDTO.incrementSuccessCount();
                } else {
                    trafficCheckDTO.incrementSlowApiCount();
                }
                return proceed;
            } catch (Exception e) {
                trafficCheckDTO.incrementFailCount();
                return joinPoint.proceed();
            }
        }
    }

    public String getApiUrl(ProceedingJoinPoint joinPoint) {
        MethodSignature joinPointSignature = (MethodSignature) joinPoint.getSignature();
        String methodName = joinPointSignature.getMethod().getName();
        return "/" + methodName;
    }

    public String getHostName () {
        try {
            String hostName = InetAddress.getLocalHost().getHostName();
            if(hostName.length() > 10) {
                return hostName.substring(Math.max(0, hostName.length() - 10));
            }
            return hostName;
        } catch (UnknownHostException e) {
            return "9999";
        }
    }

}

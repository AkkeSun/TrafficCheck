package com.example.trafficcheck;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.example.trafficcheck.countDownLatchTest.TestWorker;
import com.example.trafficcheck.dao.ApiInfoRepository;
import com.example.trafficcheck.domain.ApiInfo;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class TrafficCheckTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ApiInfoRepository apiInfoRepository;

    @Test
    @DisplayName("TrafficCheck AOP 가 Thread Safe 한지 테스트")
    void threadSafeTest() throws InterruptedException {
        int threadCount = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        CountDownLatch latch = new CountDownLatch(threadCount);

       for (int i = 0; i < threadCount; i++) {
            executorService.execute(() -> {
                try {
                    mockMvc.perform(get("/test1"));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executorService.shutdown();

        ApiInfo api = apiInfoRepository.findByPath("/test1");
        Assertions.assertEquals(100,
            Application.getTrafficCheckMap().get(api.getId()).getSuccessCount().get());
        Application.getTrafficCheckMap().clear();
    }

}

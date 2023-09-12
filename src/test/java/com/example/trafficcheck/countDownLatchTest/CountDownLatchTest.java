package com.example.trafficcheck.countDownLatchTest;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CountDownLatchTest {

    @Test
    @DisplayName("CountDownLatchTest")
    void test() throws InterruptedException {
        int threadCount = 10;
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        System.out.println("[Work Start]");
        for (int i= 0; i<threadCount; i++) {
            executorService.execute(new TestWorker(countDownLatch));
        }
        countDownLatch.await(); // latch 의 숫자가 0이 될 때 까지 기다린다
        System.out.println("[Work End]");
    }

}



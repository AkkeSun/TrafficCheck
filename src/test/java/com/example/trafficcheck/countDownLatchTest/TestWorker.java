package com.example.trafficcheck.countDownLatchTest;

import java.util.concurrent.CountDownLatch;

public class TestWorker implements Runnable{

    private final CountDownLatch countDownLatch;

    public TestWorker (CountDownLatch countDownLatch){
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(500);
            System.out.println("end");
            countDownLatch.countDown(); // latch 의 숫자를 1씩 감소시킨다
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

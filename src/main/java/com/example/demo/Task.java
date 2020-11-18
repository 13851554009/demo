package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Task {
    private static final Logger logger = LoggerFactory.getLogger(Task.class);

    @Scheduled(cron = "0/5 * * * * *")
    public void task1() throws InterruptedException {
        Thread.currentThread().sleep(1000);
        logger.info("task1 is working");
    }

    @Scheduled(cron = "0/5 * * * * *")
    public void task2() throws InterruptedException {
        Thread.currentThread().sleep(1000);
        logger.info("task2 is working");
    }
}

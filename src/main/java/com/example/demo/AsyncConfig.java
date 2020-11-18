/**
 * Copyright(C) 2019 Hangzhou DELL Technology Co., Ltd. All rights reserved.
 */
package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @author DELL
 * @date: 2019-10-15 09:58
 * @des: 定时任务线程池
 */
//@Configuration
//@EnableAsync
//public class AsyncConfig {
//
//    private int corePoolSize = 6;
//    private int maxPoolSize = 20;
//    private int queueCapacity = 30;
//
//    @Bean
//    public Executor taskExecutor() {
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setCorePoolSize(corePoolSize);
//        executor.setMaxPoolSize(maxPoolSize);
//        executor.setQueueCapacity(queueCapacity);
//        executor.initialize();
//        return executor;
//    }
//}

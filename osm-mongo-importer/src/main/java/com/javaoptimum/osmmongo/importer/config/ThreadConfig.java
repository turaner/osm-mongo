package com.javaoptimum.osmmongo.importer.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;


@Configuration
public class ThreadConfig {

    @Bean
    @Qualifier("importerExecutor")
    public ThreadPoolTaskExecutor importerExecutor() {

        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setQueueCapacity(120);
        threadPoolTaskExecutor.setCorePoolSize(16);
        threadPoolTaskExecutor.setMaxPoolSize(16);
        threadPoolTaskExecutor.setKeepAliveSeconds(0);

        RejectedExecutionHandler rejectedExecutionHandler = new ThreadPoolExecutor.CallerRunsPolicy();
        threadPoolTaskExecutor.setRejectedExecutionHandler(rejectedExecutionHandler);

        return threadPoolTaskExecutor;
    }

}

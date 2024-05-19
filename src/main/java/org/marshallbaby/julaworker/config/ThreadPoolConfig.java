package org.marshallbaby.julaworker.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class ThreadPoolConfig {

    @Bean
    public ThreadPoolExecutor julaThreadPoolExecutor(@Value("${jula.worker.capacity}") Integer julaWorkerCapacity) {

        return (ThreadPoolExecutor) Executors.newFixedThreadPool(julaWorkerCapacity);
    }


}

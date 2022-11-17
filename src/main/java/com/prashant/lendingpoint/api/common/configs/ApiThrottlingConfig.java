package com.prashant.lendingpoint.api.common.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;

@Configuration
public class ApiThrottlingConfig {

    private static final long TIME = 1L;

    @Value("${allowed.api.request}")
    private long bucketCapacity;

    @Bean
    public Bandwidth bandwidth() {
        return Bandwidth.classic(bucketCapacity, Refill.greedy(bucketCapacity, Duration.ofMinutes(TIME)));
    }

    @Bean
    public Bucket bucket() {
        return Bucket4j.builder().addLimit(bandwidth()).build();
    }

}

package com.prashant.lendingpoint.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LendingPointApiApplication {

    public static void main(final String[] args) {
        SpringApplication.run(LendingPointApiApplication.class, args);
    }

}

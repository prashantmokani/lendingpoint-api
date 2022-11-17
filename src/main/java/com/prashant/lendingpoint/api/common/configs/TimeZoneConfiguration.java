package com.prashant.lendingpoint.api.common.configs;

import org.springframework.context.annotation.Configuration;

import java.util.TimeZone;
import javax.annotation.PostConstruct;

@Configuration
public class TimeZoneConfiguration {

    @PostConstruct
    public void setup() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

}

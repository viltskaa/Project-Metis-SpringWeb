package com.example.projectmetis.service;

import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class TimeService {
    public Long getTime(){
        return Instant.now().getEpochSecond();
    }
}

package com.mvms.movie_management_system.utils;

import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DateUtils {
    public LocalDateTime getLocalDateTime() {
        return LocalDateTime.now();
    }
}

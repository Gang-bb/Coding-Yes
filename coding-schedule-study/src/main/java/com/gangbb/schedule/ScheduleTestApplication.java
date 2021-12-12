package com.gangbb.schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Gangbb
 */
@SpringBootApplication
@EnableScheduling
public class ScheduleTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScheduleTestApplication.class, args);
    }

}

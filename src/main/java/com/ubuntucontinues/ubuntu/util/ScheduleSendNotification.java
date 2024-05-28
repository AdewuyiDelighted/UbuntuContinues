package com.ubuntucontinues.ubuntu.util;

import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ScheduleSendNotification {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");

    @Scheduled(cron = "0 * 19 * * ?")
    public void scheduleTask() {
        String strDate = dateFormat.format(new Date());
        System.out.println("Cron job Scheduler: Job running at - " + strDate);
    }
}

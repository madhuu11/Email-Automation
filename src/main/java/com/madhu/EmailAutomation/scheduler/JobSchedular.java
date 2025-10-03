package com.madhu.EmailAutomation.scheduler;
//add the required imports
import com.madhu.EmailAutomation.service.BirthdayEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class JobSchedular {
//auto-wire the service class
    @Autowired
    private BirthdayEmailService BirthdayEmailService;

    // a cron expression is run every 5 min to verify the scheduler is working, use (cron = "0 0/5 * ? * *")
    @Scheduled(cron = "0 45 14 * * ?")//Runs every day at 2:45pm everyday
    public void scheduleTaskWithCronExpression() {
        // call service method sendEmail
        System.out.println("Cron Task :: Execution Start Time - " + LocalDateTime.now());
        BirthdayEmailService.sendEmail();
        System.out.println("Cron Task :: Execution End Time - " + LocalDateTime.now());
    }
    // Scheduled job for anniversary emails (e.g., daily at 2:50pm)
    @Scheduled(cron = "0 50 14 * * ?")
    public void scheduleAnniversaryEmailTask() {
        System.out.println("Anniversary Cron Task :: Execution Start Time - " + LocalDateTime.now());
        BirthdayEmailService.sendAnniversaryEmail();
        System.out.println("Anniversary Cron Task :: Execution End Time - " + LocalDateTime.now());
    }
}

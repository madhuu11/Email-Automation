package com.madhu.EmailAutomation.Scheduler;
//add the required imports
import com.madhu.EmailAutomation.Service.BirthdayEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class JobSchedular {
//auto-wire the service class
    @Autowired
    private BirthdayEmailService BirthdayEmailService;

    // a cron expression is run every 1 min to verify the scheduler is working, use (cron = "0 0/1 * 1/1 * ?")
    @Scheduled(cron = "0 0/5 * ? * *")//Runs every day at 2:45pm everyday
    public void scheduleTaskWithCronExpression() {
        // call service method sendEmail
        System.out.println("Cron Task :: Execution Start Time - " + LocalDateTime.now());
        BirthdayEmailService.sendEmail();
        System.out.println("Cron Task :: Execution End Time - " + LocalDateTime.now());
    }
}
